package com.bjj_metrics_brasil.utils;

import org.springframework.stereotype.Component;

@Component
public class ConvertDay {

    public String convert(Integer day) {
        return switch (day) {
            case 0 -> "Domingo";
            case 1 -> "Segunda";
            case 2 -> "Terça";
            case 3 -> "Quarta";
            case 4 -> "Quinta";
            case 5 -> "Sexta";
            case 6 -> "Sábado";
            default -> "UNKNOWN";
        };
    }
}
