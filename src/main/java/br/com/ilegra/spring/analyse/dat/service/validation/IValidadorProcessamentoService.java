package br.com.ilegra.spring.analyse.dat.service.validation;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.RepositoryException;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;

import java.io.File;
import java.util.List;

public interface IValidadorProcessamentoService {

    void validaParametrosLeituraRegistros(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException;

    void validarFileContendoDiretorioPadraoEntrada(File filePathInputDefault) throws RepositoryException;

    void validarContemArquivosObtidosFileDiretorioPadraoEntrada(List<File> fileInputDatList) throws RepositoryException;

    boolean isNotContainsArquivosDiretorioPadraoEntrada(File filePathInputDefault);

    boolean isDiretorioPadraoEntradaInValido(File filePathInputDefault);
}
