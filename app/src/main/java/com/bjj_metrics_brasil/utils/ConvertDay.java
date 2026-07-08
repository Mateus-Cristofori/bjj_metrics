package com.bjj_metrics_brasil.utils;

import java.time.DayOfWeek;
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

    public String convert(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "Segunda";
            case TUESDAY -> "Terça";
            case WEDNESDAY -> "Quarta";
            case THURSDAY -> "Quinta";
            case FRIDAY -> "Sexta";
            case SATURDAY -> "Sábado";
            case SUNDAY -> "Domingo";
        };
    }
}
