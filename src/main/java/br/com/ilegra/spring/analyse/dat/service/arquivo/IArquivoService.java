package br.com.ilegra.spring.analyse.dat.service.arquivo;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.util.List;

public interface IArquivoService {

    List<Arquivo> recuperarArquivos() throws ServiceException;

    void salvarArquivoResumoRelatorio(Arquivo arquivo, RelatorioArquivo relatorioArquivo) throws ServiceException;
}
