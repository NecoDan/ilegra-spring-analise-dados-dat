package br.com.ilegra.spring.analyse.dat.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static br.com.ilegra.spring.analyse.dat.utils.DateUtil.toStringLocalDateFormatada;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;

@Slf4j
public class DateUtilTest {

    @Before
    public void setUp() {
    }

    @Test
    public void deveRetornarUmaStringComADataFormatadaAPartirDataValida() {
        log.info("{} ", "#TEST: deveRetornarUmaStringComADataFormatadaAPartirDataValida: ");

        // -- 01_Cenário
        LocalDateTime d = LocalDateTime.now();
        log.info("{} ", "Data Cenario: ".concat(d.toString()));

        // -- 02_Ação
        String dataFormatada = toStringLocalDateFormatada(d);

        // -- 03_Verificacao-Validacao
        assertFalse(dataFormatada.isEmpty());
        log.info("{} ", "Data Formada: ".concat(dataFormatada));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveLancarExceptionAoTentarRetonarTextoDeUmaDataAPartirDataInvalida() {
        log.info("{} ", "#TEST: deveLancarExceptionAoTentarRetonarTextoDeUmaDataAPartirDataInvalida: ");

        // -- 01_Cenário && 02_Ação & 03_Verificação_Validação
        assertThatThrownBy(() -> toStringLocalDateFormatada(null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("DATA");
        log.info("{} ", "-------------------------------------------------------------");
    }
}
