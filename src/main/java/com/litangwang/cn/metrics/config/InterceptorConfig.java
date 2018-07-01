package com.litangwang.cn.metrics.config;

import com.boluomi.sugar.metrics.interceptor.RequestTypeAnnotationHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestTypeAnnotationHandlerInterceptor());
    }

    @Bean
    public RequestTypeAnnotationHandlerInterceptor requestTypeAnnotationHandlerInterceptor() {
        return new RequestTypeAnnotationHandlerInterceptor();
    }
}
