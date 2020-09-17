package br.com.ilegra.spring.analyse.dat.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class DateUtil {

    private DateUtil() {
    }

    public static String toStringLocalDateFormatadaPor(LocalDateTime data, String strFormato) {
        if (Objects.isNull(data))
            throw new IllegalArgumentException("Parametro DATA encontra-se inválida e/ou inexistente (NULL).");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strFormato);
        return data.format(formatter);
    }

    public static String toStringLocalDateFormatada(LocalDateTime data) {
        return toStringLocalDateFormatadaPor(data, "dd/MM/yyyy hh:mm:ss");
    }
}
