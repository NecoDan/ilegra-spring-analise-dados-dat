package br.com.ilegra.spring.analyse.dat.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.ilegra.spring.analyse.dat.utils.RandomicoUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class RandomicoUtilTest {

    @Before
    public void setUp() {
    }

    @Test
    public void deveGerarDataRandomicaAteOutroNivel() {
        log.info("{} ", "#TEST: deveGerarDataRandomicaAteOutroNivel: ");

        // -- 01_Cenário
        int anoInicial = LocalDate.now().getYear();
        int anoFinal = LocalDate.now().plusYears(2).getYear();

        // -- 02_Ação
        LocalDate dataResult = gerarDataRandomicaAte(anoInicial, anoFinal);
        boolean contains = (dataResult.getYear() >= anoInicial || dataResult.getYear() <= anoFinal);

        // -- 03_Verificacao_Validacao
        assertTrue(contains);
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveGerarValorInteiroRandomicoAte() {
        log.info("{} ", "#TEST: deveGerarValorInteiroRandomicoAte: ");

        // -- 01_Cenário
        int valorMaximo = gerarValorRandomicoInteger();

        // -- 02_Ação
        int dataResult = gerarValorRandomicoAte(valorMaximo);
        boolean contains = (dataResult > 0 && dataResult <= valorMaximo);

        // -- 03_Verificacao_Validacao
        assertTrue(contains);
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveGerarValorBigDecimalRandomicoAte() {
        log.info("{} ", "#TEST: deveGerarValorBigDecimalRandomicoAte: ");

        // -- 01_Cenário
        double valorMaximo = gerarValorRandomicoDecimal().doubleValue();

        // -- 02_Ação
        BigDecimal dataResult = gerarValorRandomicoDecimalAte(valorMaximo);
        boolean contains = (dataResult.doubleValue() > 0 && dataResult.doubleValue() <= valorMaximo);

        // -- 03_Verificacao_Validacao
        assertTrue(contains);
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveGerarDataRandomicaAte() {
        log.info("{} ", "#TEST: deveGerarDataRandomicaAte: ");

        // -- 01_Cenário
        int anoInicial = LocalDate.now().getYear();
        int anoFinal = LocalDate.now().plusYears(2).getYear();

        // -- 02_Ação
        LocalDate dataResult = gerarDataRandomicaAte(anoInicial, anoFinal);

        // -- 03_Verificacao_Validacao
        assertTrue((dataResult.getYear() >= anoInicial) || (dataResult.getYear() <= anoFinal));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveGerarDataRandomicaAteDeterminadoAno() {
        log.info("{} ", "#TEST: deveGerarDataRandomicaAteDeterminadoAno: ");

        // -- 01_Cenário
        int anoFinal = LocalDate.now().plusYears(10).getYear();

        // -- 02_Ação
        LocalDate dataResult = gerarDataRandomicaAte(anoFinal);

        // -- 03_Verificacao_Validacao
        assertTrue(dataResult.getYear() <= anoFinal);
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveGerarDataRandomicaAteDataComoParametro() {
        log.info("{} ", "#TEST: deveGerarDataRandomicaAteDataComoParametro: ");

        // -- 01_Cenário
        LocalDate dataParam = LocalDate.now();

        // -- 02_Ação
        LocalDate dataResult = gerarDataRandomicaAte(dataParam);

        // -- 03_Verificacao_Validacao
        assertEquals(dataResult.getYear(), dataParam.getYear());
        log.info("{} ", "-------------------------------------------------------------");
    }
}
