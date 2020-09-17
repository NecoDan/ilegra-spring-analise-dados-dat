package br.com.ilegra.spring.analyse.dat.repository;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.arquivo.PropriedadeArquivo;
import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoExtensaoArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.service.validation.IValidadorProcessamentoService;
import br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.buscarListaFiles;
import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.criarPathDiretorioInexistente;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ArquivoRepository implements IArquivoRepository {

    private final IValidadorProcessamentoService validadorProcessamentoService;

    @Override
    public List<Arquivo> findAllArquivos() throws RepositoryException {
        PropriedadeArquivo propriedadeArquivo = PropriedadeArquivo.builder().build();
        File filePathInputDefault = propriedadeArquivo.getFileDiretorioPadraoEntrada();
        this.validadorProcessamentoService.validarFileContendoDiretorioPadraoEntrada(filePathInputDefault);

        if (validadorProcessamentoService.isDiretorioPadraoEntradaInValido(filePathInputDefault))
            filePathInputDefault = criarPathDiretorioInexistente(propriedadeArquivo.getFileDiretorioPadraoEntrada());

        List<File> fileInputDatList = getListaFileInputDatList(filePathInputDefault);
        validadorProcessamentoService.validarContemArquivosObtidosFileDiretorioPadraoEntrada(fileInputDatList);
        return gerarArquivosListAProcessar(fileInputDatList, propriedadeArquivo);
    }

    @Override
    public void saveArquivo(Arquivo arquivo, RelatorioArquivo relatorioArquivo) throws RepositoryException {
        String conteudoArquivo = relatorioArquivo.toString();
        String nomeArquivoNovo = arquivo.getNomeArquivo().concat(".done").concat(arquivo.getExtensao());

        File pathDiretorioPadraoSaida = this.recuperarDiretorioPadraoArquivoSaida();
        File arquivoNovo = new File(pathDiretorioPadraoSaida.getAbsolutePath().concat("/").concat(nomeArquivoNovo));
        salvar(conteudoArquivo, arquivoNovo);
    }

    private List<Arquivo> gerarArquivosListAProcessar(List<File> fileInputDatList, PropriedadeArquivo propriedadeArquivo) {
        return fileInputDatList
                .stream()
                .filter(Objects::nonNull)
                .map(file -> mountArquivoFromFile(file, propriedadeArquivo))
                .collect(Collectors.toList());
    }

    private Arquivo mountArquivoFromFile(File file, PropriedadeArquivo propriedadeArquivo) {
        return Arquivo
                .builder()
                .caminho(file.getAbsolutePath())
                .build()
                .geraExtensaoArquivo(propriedadeArquivo)
                .geraNomeArquivo(propriedadeArquivo);
    }

    private List<File> getListaFileInputDatList(File filePathInputDefault) {
        List<File> fileInputDatList = new ArrayList<>();

        TipoExtensaoArquivo.getListaExtensoesArquivo()
                .parallelStream()
                .forEach(
                        extensaoArquivo -> fileInputDatList.addAll(buscarListaFiles(filePathInputDefault, extensaoArquivo))
                );

        return fileInputDatList;
    }

    private void salvar(String conteudoArquivo, File arquivoNovo) throws RepositoryException {
        try {
            ArquivoUtil.gravarArquivo(conteudoArquivo, arquivoNovo);
        } catch (IOException e) {
            throw new RepositoryException("Houve erro ao criar o arquivo de saida (output) com o resumo do relatorio.".concat(e.getLocalizedMessage()));
        } finally {
            log.info("Arquivo criado com sucesso: " + arquivoNovo.getAbsolutePath());
        }
    }

    private File recuperarDiretorioPadraoArquivoSaida() {
        PropriedadeArquivo propriedadeArquivo = PropriedadeArquivo.builder().build();
        File pathDiretorioPadraoSaida = new File(propriedadeArquivo.getDiretorioPadraoArquivosSaida());
        return (pathDiretorioPadraoSaida.exists()) ? pathDiretorioPadraoSaida : criarPathDiretorioInexistente(pathDiretorioPadraoSaida);
    }
}
