package com.tokiomarine.desafio.utils;

import java.math.BigDecimal;

public class CalculaUtils {


    public static BigDecimal calcularTaxa(final long dias, final BigDecimal valor) {
        if (dias == 0) {
            return BigDecimal.valueOf(3).add(valor.multiply(BigDecimal.valueOf(0.025)));
        }
        if (dias >= 1 && dias <= 10) {
            return BigDecimal.valueOf(12);
        }
        if (dias >= 11 && dias <= 20) {
            return valor.multiply(BigDecimal.valueOf(0.082));
        }
        if (dias >= 21 && dias <= 30) {
            return valor.multiply(BigDecimal.valueOf(0.069));
        }
        if (dias >= 31 && dias <= 40) {
            return valor.multiply(BigDecimal.valueOf(0.047));
        }
        if (dias >= 41 && dias <= 50) {
            return valor.multiply(BigDecimal.valueOf(0.017));
        }
        return null;
    }
}
