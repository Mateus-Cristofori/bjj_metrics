package com.bjj_metrics_brasil.annotation.register;

import com.bjj_metrics_brasil.annotation.resolver.CurrentAthleteIdResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CurrentAthleteIdRegisterResolver implements WebMvcConfigurer {

    @Autowired
    private CurrentAthleteIdResolver currentAthleteIdResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentAthleteIdResolver);
    }
}
