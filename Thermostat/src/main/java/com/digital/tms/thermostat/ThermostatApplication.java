package com.digital.tms.thermostat;

import com.digital.tms.thermostat.config.ApiConfig;
import com.digital.tms.thermostat.config.ThermostatConfig;
import com.digital.tms.thermostat.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ApiConfig.class, ThermostatConfig.class})
public class ThermostatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThermostatApplication.class, args);
    }

    @Autowired
    RequestService service;


    @Bean
    CommandLineRunner run() {
        return args -> {
            service.startThermostat();
        };
    }
}
