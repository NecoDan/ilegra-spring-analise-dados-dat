package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.util.Optional;

public interface IFactoryLeituraArquivoConversorService {

    boolean isAppliable(TipoLayoutArquivo tipoLayoutArquivo);

    Optional<ResumoRelatorio> obterResumoRelatorioAoLerLinhaRegistroArquivo(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException;
}
