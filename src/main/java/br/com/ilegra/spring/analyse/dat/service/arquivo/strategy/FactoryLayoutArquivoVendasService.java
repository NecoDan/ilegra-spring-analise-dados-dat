package br.com.ilegra.spring.analyse.dat.service.arquivo.strategy;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FactoryLayoutArquivoVendasService implements IFactoryTipoLayoutArquivoService {

    @Override
    public boolean isAppliable(String linhaArquivo) {
        return (Objects.nonNull(linhaArquivo) && !linhaArquivo.isEmpty() && isAppliableLayoutVendas(linhaArquivo));
    }

    @Override
    public TipoLayoutArquivo obterTipoLayoutArquivo(String linhaArquivo) {
        return (isAppliable(linhaArquivo)) ? TipoLayoutArquivo.LAYOUT_VENDAS : TipoLayoutArquivo.NENHUM;
    }

    private boolean isAppliableLayoutVendas(String linhaArquivo) {
        String chave = linhaArquivo.substring(0, 3);
        return chave.equals(TipoLayoutArquivo.LAYOUT_VENDAS.getCodigoLiteral());
    }
}
