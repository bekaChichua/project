package com.digital.tms.thermostat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Scheduler {
    private final RequestService service;
    @Scheduled(fixedDelay = 2000)
    public void sendTemperature() {
        if(ApplicationState.CRON_START){
            service.sendTempData(ApplicationState.THERMOSTAT_NAME);
        }
    }
}
