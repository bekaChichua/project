package com.digital.tms.thermostat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final ApiConfig config;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .setReadTimeout(Duration.ofSeconds(config.readTimeOut()))
                .setConnectTimeout(Duration.ofSeconds(config.connectionTimeOut()))
                .rootUri(config.url())
                .build();
    }
}
