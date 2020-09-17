package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.negocio.cadastro.VendedorService;
import br.com.ilegra.spring.analyse.dat.service.validation.ValidadorProcessamentoService;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.reset;

@Slf4j
public class BuilderResumoRelatorioLayoutVendedorServiceTest {

    @Mock
    private VendedorService VendedorServiceMock;
    @Spy
    @InjectMocks
    private BuilderResumoRelatorioLayoutVendedorService builderResumoRelatorioLayoutVendedorServiceMock;

    private BuilderResumoRelatorioLayoutVendedorService builderResumoRelatorioLayoutVendedorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ValidadorProcessamentoService validadorProcessamentoService = new ValidadorProcessamentoService();
        VendedorService vendedorService = new VendedorService(validadorProcessamentoService);
        this.builderResumoRelatorioLayoutVendedorService = new BuilderResumoRelatorioLayoutVendedorService(vendedorService);
    }

    private void resetarMocks() {
        reset(VendedorServiceMock);
        reset(builderResumoRelatorioLayoutVendedorServiceMock);
    }

    @Test
    public void naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVendedor() throws ServiceException {
        log.info("{} ", "#TEST: naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVendedor: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_VENDEDOR;
        String linhaArquivo = "001ç1234567891234çPedroç50000";

        // -- 02_Ação
        doCallRealMethod().when(builderResumoRelatorioLayoutVendedorServiceMock).obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutVendedorServiceMock.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertFalse(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVendedor() throws ServiceException {
        log.info("{} ", "#TEST: deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVendedor: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_VENDEDOR;
        String linhaArquivo = "001ç1234567891234çPedroç50000";

        // -- 02_Ação
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutVendedorService.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
