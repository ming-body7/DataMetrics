package com.litangwang.metrics.config;

import com.litangwang.metrics.DataMetricsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class MetricsConfig {
    @Bean
    public OncePerRequestFilter dataLogFilter() {
        return new DataMetricsFilter();
    }
}
