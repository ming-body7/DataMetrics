package com.litangwang.metrics.config;

import com.litangwang.metrics.DataMetricsFilter;
import com.litangwang.metrics.DataMetricsReporter;
import com.litangwang.metrics.DataMetricsWriter;
import com.litangwang.metrics.impl.DataMetricsReporterImpl;
import com.litangwang.metrics.impl.DataMetricsWriterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class MetricsConfig {
    @Bean
    public OncePerRequestFilter dataLogFilter() {
        return new DataMetricsFilter();
    }

    @Bean
    public DataMetricsReporter dataMetricsReporter() {
        return new DataMetricsReporterImpl();
    }

    @Bean
    public DataMetricsWriter dataMetricsWriter() {
        return new DataMetricsWriterImpl();
    }
}
