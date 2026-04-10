package com.bjj_metrics_brasil.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class CalculatePercentage {

    public double calculatePercentage(long value, long total) {
        if (total == 0) {
            return 0;
        }

        return BigDecimal
            .valueOf(value)
            .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}
