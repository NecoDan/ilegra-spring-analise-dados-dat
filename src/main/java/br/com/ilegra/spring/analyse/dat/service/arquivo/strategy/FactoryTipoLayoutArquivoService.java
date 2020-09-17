package br.com.ilegra.spring.analyse.dat.service.arquivo.strategy;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class FactoryTipoLayoutArquivoService {

    private final List<IFactoryTipoLayoutArquivoService> factoryTipoLayoutArquivoServiceList;

    public FactoryTipoLayoutArquivoService(List<IFactoryTipoLayoutArquivoService> factoryTipoLayoutArquivoServiceList) {
        this.factoryTipoLayoutArquivoServiceList = factoryTipoLayoutArquivoServiceList;
    }

    public TipoLayoutArquivo obterTipoLayoutArquivo(String linhaArquivo) {
        Optional<TipoLayoutArquivo> optionalTipoLayoutArquivo = this.factoryTipoLayoutArquivoServiceList.stream()
                .filter(Objects::nonNull)
                .filter(factoryTipoLayoutArquivoService -> factoryTipoLayoutArquivoService.isAppliable(linhaArquivo))
                .map(factoryTipoLayoutArquivoService -> factoryTipoLayoutArquivoService.obterTipoLayoutArquivo(linhaArquivo))
                .findFirst();
        return optionalTipoLayoutArquivo.orElse(TipoLayoutArquivo.NENHUM);
    }
}
