package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.negocio.cadastro.IVendedorService;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuilderResumoRelatorioLayoutVendedorService implements IFactoryLeituraArquivoConversorService {

    private final IVendedorService vendedorService;

    @Override
    public boolean isAppliable(TipoLayoutArquivo tipoLayoutArquivo) {
        return (Objects.nonNull(tipoLayoutArquivo) && tipoLayoutArquivo.isLayoutVendedor());
    }

    @Override
    public Optional<ResumoRelatorio> obterResumoRelatorioAoLerLinhaRegistroArquivo(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        return (isAppliable(tipoLayoutArquivo)) ? this.vendedorService.getResumoRelatorioVendedor(tipoLayoutArquivo, linhaArquivo) : Optional.empty();
    }
}
