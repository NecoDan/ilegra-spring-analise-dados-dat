package br.com.ilegra.spring.analyse.dat.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomicoUtil {

    private RandomicoUtil() {
    }

    private static Random getRandom() {
        return new Random();
    }

    private static int gerarValorRandomico() {
        int min = 1;
        int max = 5000;
        return min + getRandom().nextInt(max);
    }

    public static Long gerarValorRandomicoLong() {
        return (long) gerarValorRandomico();
    }

    public static Integer gerarValorRandomicoInteger() {
        return gerarValorRandomico();
    }

    public static Integer gerarValorRandomicoAte(int max) {
        int min = 1;
        return min + getRandom().nextInt(max);
    }

    public static BigDecimal gerarValorRandomicoDecimal() {
        double leftLimit = 1D;
        double rightLimit = 1000D;
        return BigDecimal.valueOf(leftLimit + getRandom().nextDouble() * (rightLimit - leftLimit)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal gerarValorRandomicoDecimalAte(double max) {
        double leftLimit = 1D;
        double rightLimit = 1000D;

        if (max > 0.0)
            rightLimit = max;

        return BigDecimal.valueOf(leftLimit + getRandom().nextDouble() * (rightLimit - leftLimit)).setScale(2, RoundingMode.HALF_UP);
    }

    public static LocalDate gerarDataRandomicaAte(int ano) {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(ano, 12, 31).toEpochDay();

        long randoDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randoDay);
    }

    public static LocalDate gerarDataRandomicaAte(int anoInicial, int anoFinal) {
        long minDay = LocalDate.of(anoInicial, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(anoFinal, 12, 31).toEpochDay();

        long randoDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randoDay);
    }

    public static LocalDate gerarDataRandomicaAte(LocalDate data) {
        long minDay = LocalDate.of(data.getYear(), 1, 1).toEpochDay();
        long maxDay = LocalDate.of(data.getYear(), 12, 31).toEpochDay();

        long randoDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randoDay);
    }

    public static String gerarCpfRandomico(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2));
        String retorno = null;

        if (d2 >= 10)
            d2 = 0;
        retorno = "";

        retorno = (comPontos)
                ? "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2
                : "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return retorno;
    }

    public static String gerarCnpjRandomico(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = 0; // randomiza(n);
        int n10 = 0; // randomiza(n);
        int n11 = 0; // randomiza(n);
        int n12 = 1; // randomiza(n);
        int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4
                + n1 * 5;

        d1 = 11 - (mod(d1));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4
                + n2 * 5 + n1 * 6;

        d2 = 11 - (mod(d2));

        if (d2 >= 10)
            d2 = 0;

        String retorno = null;

        if (comPontos)
            retorno = "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1
                    + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;

        return retorno;
    }

    private static int randomiza(int n) {
        return (int) (Math.random() * n);
    }

    private static int mod(int dividendo) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / 11) * 11));
    }

}
