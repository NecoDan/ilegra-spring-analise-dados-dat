package br.com.ilegra.spring.analyse.dat.service.arquivo.strategy;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;

public interface IFactoryTipoLayoutArquivoService {

    boolean isAppliable(String linhaArquivo);

    TipoLayoutArquivo obterTipoLayoutArquivo(String linhaArquivo);
}
