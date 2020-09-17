package br.com.ilegra.spring.analyse.dat.service.negocio.cadastro;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.util.Optional;

public interface IVendedorService {
    Optional<ResumoRelatorio> getResumoRelatorioVendedor(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException;
}
