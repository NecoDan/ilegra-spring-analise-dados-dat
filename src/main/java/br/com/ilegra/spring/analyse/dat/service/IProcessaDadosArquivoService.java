package br.com.ilegra.spring.analyse.dat.service;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.util.List;

public interface IProcessaDadosArquivoService {

    void efetuarProcessamento() throws ServiceException;

    void gerarProcessamento(Arquivo arquivo);

    void processar(Arquivo arquivo, List<ResumoRelatorio> resumoRelatorioList) throws ServiceException;

    List<ResumoRelatorio> obterResumosRelatorios(Arquivo arquivo) throws ServiceException;
}
