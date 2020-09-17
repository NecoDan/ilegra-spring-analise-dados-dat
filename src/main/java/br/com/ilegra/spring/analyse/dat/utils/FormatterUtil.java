package br.com.ilegra.spring.analyse.dat.utils;

import java.math.BigDecimal;
import java.util.Objects;

public final class FormatterUtil {

    private FormatterUtil() {
    }

    public static BigDecimal onlyBigDecimal(String base) {
        return (Objects.isNull(base) || base.isEmpty()) ? BigDecimal.ZERO : getOnlyBigDecimal(base);
    }

    private static BigDecimal getOnlyBigDecimal(String base) {
        if (base.contains(","))
            base = base.replace(',', '.');
        return new BigDecimal(base);
    }

    public static long onlyLongNumbers(String base) {
        return (Objects.isNull(base) || base.isEmpty()) ? 0L : Long.parseLong(base.replaceAll("\\D", ""));
    }
}
