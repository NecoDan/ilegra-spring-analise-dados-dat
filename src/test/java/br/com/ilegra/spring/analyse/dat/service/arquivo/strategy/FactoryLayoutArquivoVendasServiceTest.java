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
public class FactoryLayoutArquivoVendasServiceTest {

    @Spy
    @InjectMocks
    private FactoryLayoutArquivoVendasService factoryLayoutArquivoVendasService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(factoryLayoutArquivoVendasService);
    }

    @Test
    public void obterTipoLayoutArquivoIsVendedor() {
        log.info("{} ", "#TEST: obterTipoLayoutArquivoIsVendedor: ");

        // -- 01_Cenário
        resetarMocks();
        String linhaArquivo = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        // -- 02_Ação
        doCallRealMethod().when(factoryLayoutArquivoVendasService).obterTipoLayoutArquivo(linhaArquivo);
        TipoLayoutArquivo tipoLayoutArquivo = factoryLayoutArquivoVendasService.obterTipoLayoutArquivo(linhaArquivo);

        // -- 03_Verificação_Validação
        assertTrue(Objects.nonNull(tipoLayoutArquivo) && tipoLayoutArquivo.isLayoutVendas());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
