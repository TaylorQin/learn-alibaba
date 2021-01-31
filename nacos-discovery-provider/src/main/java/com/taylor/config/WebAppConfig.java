package com.taylor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig {

    @Bean
    public WebMvcConfigurer WebMvcConfigurer(@Autowired AccessTokenInterceptor accessTokenInterceptor) {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(accessTokenInterceptor).addPathPatterns("/echo/*");
            };
        };
    }
}
