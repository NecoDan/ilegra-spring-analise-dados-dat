package br.com.ilegra.spring.analyse.dat.service.layouts;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.negocio.cadastro.ClienteService;
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
public class BuilderResumoRelatorioLayoutClienteServiceTest {

    @Mock
    private ClienteService clienteServiceMock;
    @Spy
    @InjectMocks
    private BuilderResumoRelatorioLayoutClienteService builderResumoRelatorioLayoutClienteServiceMock;
    private BuilderResumoRelatorioLayoutClienteService builderResumoRelatorioLayoutClienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ValidadorProcessamentoService validadorProcessamentoService = new ValidadorProcessamentoService();
        ClienteService clienteService = new ClienteService(validadorProcessamentoService);
        this.builderResumoRelatorioLayoutClienteService = new BuilderResumoRelatorioLayoutClienteService(clienteService);
    }

    private void resetarMocks() {
        reset(clienteServiceMock);
        reset(builderResumoRelatorioLayoutClienteServiceMock);
    }

    @Test
    public void naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutCliente() throws ServiceException {
        log.info("{} ", "#TEST: naoDeveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutCliente: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_CLIENTE;
        String linhaArquivo = "002ç2345675434544345çJose da SilvaçRural";

        // -- 02_Ação
        doCallRealMethod().when(builderResumoRelatorioLayoutClienteServiceMock).obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutClienteServiceMock.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertFalse(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutCliente() throws ServiceException {
        log.info("{} ", "#TEST: deveObterResumoRelatorioAoLerLinhaRegistroArquivoIsTipoLayoutCliente: ");

        // -- 01_Cenário
        resetarMocks();
        TipoLayoutArquivo tipoLayoutArquivo = TipoLayoutArquivo.LAYOUT_CLIENTE;
        String linhaArquivo = "002ç2345675434544345çJose da SilvaçRural";

        // -- 02_Ação
        Optional<ResumoRelatorio> optionalResumoRelatorio = builderResumoRelatorioLayoutClienteService.obterResumoRelatorioAoLerLinhaRegistroArquivo(tipoLayoutArquivo, linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(optionalResumoRelatorio.isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
