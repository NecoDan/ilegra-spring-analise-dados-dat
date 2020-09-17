package br.com.ilegra.spring.analyse.dat.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static br.com.ilegra.spring.analyse.dat.utils.FormatterUtil.onlyBigDecimal;
import static br.com.ilegra.spring.analyse.dat.utils.FormatterUtil.onlyLongNumbers;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FormatterUtilTest {

    @Before
    public void setUp() {
    }

    @Test
    public void deveRetornarValorNumeroInteiroLongoAPartirTexto() {
        log.info("{} ", "#TEST: deveRetornarValorNumeroInteiroLongoAPartirTexto: ");

        // -- 01_Cenário
        String texto = String.valueOf(RandomicoUtil.gerarValorRandomicoLong());
        log.info("{} ", "Numero caractere Cenario: ".concat(texto));

        // -- 02_Ação
        long numero1 = onlyLongNumbers(texto);

        // -- 03_Verificacao-Validacao
        assertTrue(numero1 > 0);
        assertEquals(String.valueOf(numero1), texto);

        log.info("{} ", "Numero: ".concat(String.valueOf(numero1)));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveRetornarValorZeradoNumeroInteiroLongoAPartirTexto() {
        log.info("{} ", "#TEST: deveRetornarValorZeradoNumeroInteiroLongoAPartirTexto: ");

        // -- 01_Cenário
        String texto = "";
        log.info("{} ", "Numero caractere Cenario: ".concat(texto));

        // -- 02_Ação
        long numero1 = onlyLongNumbers(texto);

        // -- 03_Verificacao-Validacao
        assertTrue(numero1 <= 0);

        log.info("{} ", "Numero: ".concat(String.valueOf(numero1)));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveRetornarValorNumericoDecimal() {
        log.info("{} ", "#TEST: deveRetornarValorNumericoDecimal: ");

        // -- 01_Cenário
        String texto = String.valueOf(RandomicoUtil.gerarValorRandomicoDecimal());
        log.info("{} ", "Valor Cenario: ".concat(texto));

        // -- 02_Ação
        BigDecimal valor = onlyBigDecimal(texto);

        // -- 03_Verificacao-Validacao
        assertTrue(valor.doubleValue() > 0.0);
        log.info("{} ", "Valor saida: ".concat(String.valueOf(valor)));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveRetornarValorZeradoNumericoDecimal() {
        log.info("{} ", "#TEST: deveRetornarValorZeradoNumericoDecimal: ");

        // -- 01_Cenário
        String texto = "";
        log.info("{} ", "Valor Cenario: ".concat(texto));

        // -- 02_Ação
        BigDecimal valor = onlyBigDecimal(texto);

        // -- 03_Verificacao-Validacao
        assertTrue(valor.doubleValue() <= 0.0);
        log.info("{} ", "Valor saida: ".concat(String.valueOf(valor)));
        log.info("{} ", "-------------------------------------------------------------");
    }
}
