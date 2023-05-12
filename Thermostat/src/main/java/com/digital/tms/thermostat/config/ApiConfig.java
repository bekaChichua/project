package com.digital.tms.thermostat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

@ConfigurationProperties(prefix = "api")
public record ApiConfig(
            String url,
            HttpMethod method,
            int readTimeOut,
            int connectionTimeOut
        ) {
}
