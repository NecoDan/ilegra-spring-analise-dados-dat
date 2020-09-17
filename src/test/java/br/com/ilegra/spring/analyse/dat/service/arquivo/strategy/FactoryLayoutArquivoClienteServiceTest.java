package br.com.ilegra.spring.analyse.dat.service.arquivo.strategy;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.reset;

@Slf4j
public class FactoryLayoutArquivoClienteServiceTest {

    @Spy
    @InjectMocks
    private FactoryLayoutArquivoClienteService factoryLayoutArquivoClienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(factoryLayoutArquivoClienteService);
    }

    @Test
    public void obterTipoLayoutArquivoIsCliente() {
        log.info("{} ", "#TEST: obterTipoLayoutArquivoIsCliente: ");

        // -- 01_Cenário
        resetarMocks();
        String linhaArquivo = "002ç2345675434544345çJose da SilvaçRural";

        // -- 02_Ação
        doCallRealMethod().when(factoryLayoutArquivoClienteService).obterTipoLayoutArquivo(linhaArquivo);
        TipoLayoutArquivo tipoLayoutArquivo = factoryLayoutArquivoClienteService.obterTipoLayoutArquivo(linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(Objects.nonNull(tipoLayoutArquivo) && tipoLayoutArquivo.isLayoutCliente());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
