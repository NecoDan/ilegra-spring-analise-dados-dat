package br.com.ilegra.spring.analyse.dat.service;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import br.com.ilegra.spring.analyse.dat.service.arquivo.IArquivoService;
import br.com.ilegra.spring.analyse.dat.service.arquivo.strategy.FactoryTipoLayoutArquivoService;
import br.com.ilegra.spring.analyse.dat.service.layouts.FactoryLeituraArquivoConversorService;
import br.com.ilegra.spring.analyse.dat.service.negocio.IObtemLotesService;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.getLinhasRegistrosArquivos;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessaDadosArquivoService implements IProcessaDadosArquivoService {

    private final IArquivoService arquivoService;
    private final FactoryTipoLayoutArquivoService factoryTipoLayoutArquivoService;
    private final FactoryLeituraArquivoConversorService factoryLeituraArquivoConversorService;
    private final IObtemLotesService obtemLotesService;

    @Override
    public void efetuarProcessamento() throws ServiceException {
        List<Arquivo> arquivoList = arquivoService.recuperarArquivos();
        arquivoList.parallelStream().filter(Objects::nonNull).forEach(this::gerarProcessamento);
    }

    @Override
    public void gerarProcessamento(Arquivo arquivo) throws ServiceException {
        processar(arquivo, obterResumosRelatorios(arquivo));
    }

    @Override
    public void processar(Arquivo arquivo, List<ResumoRelatorio> resumoRelatorioList) throws ServiceException {
        if (Objects.isNull(resumoRelatorioList) || resumoRelatorioList.isEmpty())
            return;

        List<Vendedor> vendedorList = obtemLotesService.getVendedoresResumoRelatorio(resumoRelatorioList);
        List<Cliente> clienteList = obtemLotesService.getClientesResumoRelatorio(resumoRelatorioList);
        List<Venda> vendasList = obtemLotesService.getVendasResumoRelatorio(resumoRelatorioList);

        RelatorioArquivo relatorioArquivo = RelatorioArquivo.builder()
                .build()
                .somaQtdeClientes(clienteList)
                .somaQtdeVendedores(vendedorList)
                .identificaVendaMaisCaraProcessada(vendasList)
                .identificaPiorVendedorVendasProcessadas(vendasList);

        arquivoService.salvarArquivoResumoRelatorio(arquivo, relatorioArquivo);
    }

    @Override
    public List<ResumoRelatorio> obterResumosRelatorios(Arquivo arquivo) throws ServiceException {
        try {
            List<ResumoRelatorio> resumoRelatorioList = new ArrayList<>();
            List<String> linhasArquivoList = getLinhasRegistrosArquivos(arquivo.getCaminho());

            linhasArquivoList
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .forEach(linhaArquivo -> gerarResumosRelatoriosListPor(resumoRelatorioList, linhaArquivo));

            return resumoRelatorioList;
        } catch (IOException e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }

    private void gerarResumosRelatoriosListPor(List<ResumoRelatorio> resumoRelatorioList, String linhaArquivo) {
        TipoLayoutArquivo tipoLayoutArquivo = factoryTipoLayoutArquivoService.obterTipoLayoutArquivo(linhaArquivo);
        Optional<ResumoRelatorio> optionalResumoRelatorio = factoryLeituraArquivoConversorService.getResumoRelatorio(tipoLayoutArquivo, linhaArquivo);
        optionalResumoRelatorio.ifPresent(resumoRelatorioList::add);
    }
}
