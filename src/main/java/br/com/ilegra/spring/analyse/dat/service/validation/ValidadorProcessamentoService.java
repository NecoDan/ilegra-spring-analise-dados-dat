package br.com.ilegra.spring.analyse.dat.service.validation;


import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.RepositoryException;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ValidadorProcessamentoService implements IValidadorProcessamentoService {

    @Override
    public void validaParametrosLeituraRegistros(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        if (Objects.isNull(tipoLayoutArquivo))
            throw new ServiceException("Nenhum tipo de layout arquivo definido e/ou layout encontra-se inválido e/ou inexistente.");

        if (Objects.isNull(linhaArquivo) || linhaArquivo.isEmpty())
            throw new ServiceException("Conteudo da linha para separacao, encontra-se inválida (vazia) e/ou inexistente (null).");
    }

    @Override
    public void validarFileContendoDiretorioPadraoEntrada(File filePathInputDefault) throws RepositoryException {
        if (isNotContainsArquivosDiretorioPadraoEntrada(filePathInputDefault))
            throw new RepositoryException("Não existem arquivos a serem lidos no diretório padrão de entrada dos arquivos. Pasta vazia e/ou inexistentes.");
    }

    @Override
    public void validarContemArquivosObtidosFileDiretorioPadraoEntrada(List<File> fileInputDatList) throws RepositoryException {
        if (fileInputDatList.isEmpty())
            throw new RepositoryException("Não foram encontrados arquivos de leitura para entrada de dados. Pasta vazia.");
    }

    @Override
    public boolean isNotContainsArquivosDiretorioPadraoEntrada(File filePathInputDefault) {
        return (Objects.isNull(filePathInputDefault.listFiles())
                || (Objects.nonNull(filePathInputDefault.listFiles())
                && Objects.requireNonNull(filePathInputDefault.listFiles()).length == 0));
    }

    @Override
    public boolean isDiretorioPadraoEntradaInValido(File filePathInputDefault) {
        return (!filePathInputDefault.exists() || !filePathInputDefault.isDirectory() || !filePathInputDefault.canRead());
    }
}
