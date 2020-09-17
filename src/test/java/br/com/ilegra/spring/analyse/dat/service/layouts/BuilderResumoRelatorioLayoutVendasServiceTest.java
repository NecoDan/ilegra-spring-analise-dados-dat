package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.negocio.vendas.VendaService;
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
public class BuilderResumoRelatorioLayoutVendasServiceTest {

    @Mock
    private VendaService vendaServiceMock;
    @Spy
    @InjectMocks
    private BuilderResumoRelatorioLayoutVendasService builderResumoRelatorioLayoutVendasServiceMock;

    private BuilderResumoRelatorioLayoutVendasService builderResumoRelatorioLayoutVendasService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ValidadorProcessamentoService validadorProcessamentoService = new ValidadorProcessamentoService();
        VendaService vendaService = new VendaService(validadorProcessamentoService);
        this.builderResumoRelatorioLayoutVendasService = new BuilderResumoRelatorioLayoutVendasService(vendaService);
    }

    private void resetarMocks() {
        reset(vendaServiceMock);
        reset(builderResumoRelatorioLayoutVendasServiceMock);
    }

    @Test
    public void naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVenda() throws ServiceException {
        log.info("{} ", "#TEST: naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVenda: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_VENDAS;
        String linhaArquivo = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";

        // -- 02_Ação
        doCallRealMethod().when(builderResumoRelatorioLayoutVendasServiceMock).obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutVendasServiceMock.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertFalse(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVenda() throws ServiceException {
        log.info("{} ", "#TEST: deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutVenda: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_VENDAS;
        String linhaArquivo = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";

        // -- 02_Ação
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutVendasService.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
