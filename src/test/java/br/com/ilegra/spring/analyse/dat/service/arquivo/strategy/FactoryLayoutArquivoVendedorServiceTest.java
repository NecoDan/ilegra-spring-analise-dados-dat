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
public class FactoryLayoutArquivoVendedorServiceTest {

    @Spy
    @InjectMocks
    private FactoryLayoutArquivoVendedorService factoryLayoutArquivoVendedorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(factoryLayoutArquivoVendedorService);
    }

    @Test
    public void obterTipoLayoutArquivoIsVendedor() {
        log.info("{} ", "#TEST: obterTipoLayoutArquivoIsVendedor: ");

        // -- 01_Cenário
        resetarMocks();
        String linhaArquivo = "001ç1234567891234çPedroç50000";

        // -- 02_Ação
        doCallRealMethod().when(factoryLayoutArquivoVendedorService).obterTipoLayoutArquivo(linhaArquivo);
        TipoLayoutArquivo tipoLayoutArquivo = factoryLayoutArquivoVendedorService.obterTipoLayoutArquivo(linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(Objects.nonNull(tipoLayoutArquivo) && tipoLayoutArquivo.isLayoutVendedor());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
