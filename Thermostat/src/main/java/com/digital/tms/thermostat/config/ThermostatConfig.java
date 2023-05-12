package com.digital.tms.thermostat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "thermostat")
public record ThermostatConfig(String tempEndpoint, String getAllEndpoint, Integer max, Integer min) {
}
