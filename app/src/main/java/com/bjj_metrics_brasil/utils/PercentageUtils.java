package com.bjj_metrics_brasil.utils;

import com.bjj_metrics_brasil.statistics.model.commons.TechniqueStats;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PercentageUtils {

    public void normalizeTo100(List<TechniqueStats> list) {
        if (list == null || list.isEmpty()) return;

        double total = list.stream().mapToDouble(TechniqueStats::getValue).sum();

        double diff = 100.0 - total;

        if (Math.abs(diff) < 0.01) return;

        TechniqueStats target = list
            .stream()
            .max(Comparator.comparingDouble(TechniqueStats::getValue))
            .orElse(list.getFirst());

        target.setValue(target.getValue() + diff);
    }
}
