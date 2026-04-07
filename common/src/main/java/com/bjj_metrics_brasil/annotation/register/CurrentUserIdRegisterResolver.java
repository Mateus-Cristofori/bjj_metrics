package com.bjj_metrics_brasil.annotation.register;

import com.bjj_metrics_brasil.annotation.resolver.CurrentUserIdResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CurrentUserIdRegisterResolver implements WebMvcConfigurer {

    @Autowired
    private CurrentUserIdResolver currentUserIdResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserIdResolver);
    }
}
