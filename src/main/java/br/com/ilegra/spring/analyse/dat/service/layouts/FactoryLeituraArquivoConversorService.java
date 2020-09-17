package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FactoryLeituraArquivoConversorService {

    private final List<IFactoryLeituraArquivoConversorService> factoryLeituraArquivoConversorServiceList;

    public FactoryLeituraArquivoConversorService(List<IFactoryLeituraArquivoConversorService> factoryLeituraArquivoConversorServiceList) {
        this.factoryLeituraArquivoConversorServiceList = factoryLeituraArquivoConversorServiceList;
    }

    public Optional<ResumoRelatorio> getResumoRelatorio(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        for (IFactoryLeituraArquivoConversorService factoryLeituraArquivoToResumoRelatorio : this.factoryLeituraArquivoConversorServiceList) {
            if (factoryLeituraArquivoToResumoRelatorio.isAppliable(tipoLayoutArquivo))
                return factoryLeituraArquivoToResumoRelatorio.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);
        }
        return Optional.empty();
    }
}
