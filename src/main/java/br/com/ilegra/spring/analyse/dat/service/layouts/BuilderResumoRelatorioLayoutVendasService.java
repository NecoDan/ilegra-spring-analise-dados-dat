package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.negocio.vendas.IVendaService;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuilderResumoRelatorioLayoutVendasService implements IFactoryLeituraArquivoConversorService {

    private final IVendaService vendaService;

    @Override
    public boolean isAppliable(TipoLayoutArquivo tipoLayoutArquivo) {
        return (Objects.nonNull(tipoLayoutArquivo) && tipoLayoutArquivo.isLayoutVendas());
    }

    @Override
    public Optional<ResumoRelatorio> obterResumoRelatorioAoLerLinhaRegistroArquivo(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        return (isAppliable(tipoLayoutArquivo)) ? this.vendaService.getResumoRelatorioVendas(tipoLayoutArquivo, linhaArquivo) : Optional.empty();
    }
}
