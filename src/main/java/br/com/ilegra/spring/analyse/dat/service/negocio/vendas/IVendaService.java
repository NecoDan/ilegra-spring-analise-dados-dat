package br.com.ilegra.spring.analyse.dat.service.negocio.vendas;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.util.Optional;

public interface IVendaService {
    Optional<ResumoRelatorio> getResumoRelatorioVendas(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException;
}
