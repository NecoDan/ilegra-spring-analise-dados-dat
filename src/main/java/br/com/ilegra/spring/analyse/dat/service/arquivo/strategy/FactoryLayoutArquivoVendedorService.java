package br.com.ilegra.spring.analyse.dat.service.arquivo.strategy;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FactoryLayoutArquivoVendedorService implements IFactoryTipoLayoutArquivoService {

    @Override
    public boolean isAppliable(String linhaArquivo) {
        return (Objects.nonNull(linhaArquivo) && !linhaArquivo.isEmpty() && isAppliableLayoutVendedor(linhaArquivo));
    }

    @Override
    public TipoLayoutArquivo obterTipoLayoutArquivo(String linhaArquivo) {
        return (isAppliable(linhaArquivo)) ? TipoLayoutArquivo.LAYOUT_VENDEDOR : TipoLayoutArquivo.NENHUM;
    }

    private boolean isAppliableLayoutVendedor(String linhaArquivo) {
        String chave = linhaArquivo.substring(0, 3);
        return chave.equals(TipoLayoutArquivo.LAYOUT_VENDEDOR.getCodigoLiteral());
    }
}
