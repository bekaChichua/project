package com.digital.tms.controller;

import com.digital.tms.model.dto.ArrayResponse;
import com.digital.tms.model.dto.TemperatureRequest;
import com.digital.tms.model.dto.ThermostatDTO;
import com.digital.tms.model.dto.ThermostatTemperatureResponse;
import com.digital.tms.service.ThermostatService;
import com.digital.tms.service.iServicies.IThermostatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.digital.tms.model.dto.ThermostatTemperatureResponse.ThermostatState.CRITICAL;
import static com.digital.tms.model.dto.ThermostatTemperatureResponse.ThermostatState.NORMAL;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/thermostat")
public class ThermostatController {
    private final IThermostatService service;

    @GetMapping("/all")
    public ArrayResponse<ThermostatDTO> getAll() {
        return new ArrayResponse<>(service.getAll());
    }
    @GetMapping
    public ArrayResponse<ThermostatDTO> getAll(Authentication authentication) {
        return new ArrayResponse<>(service.getAll(authentication.getName()));
    }

    @GetMapping("/{thermostatName}")
    public ThermostatDTO getThermostatBy(Authentication authentication, @PathVariable String thermostatName) {
        return service.getThermostatBy(authentication.getName(), thermostatName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addThermostat(Authentication authentication, @Valid @RequestBody ThermostatDTO thermostat) {
        service.addThermostat(authentication.getName(), thermostat);
    }

    @DeleteMapping("/{thermostatName}")
    public void deleteThermostatBy(Authentication authentication, @PathVariable String thermostatName) {
        service.deleteThermostatBy(authentication.getName(), thermostatName);
    }

    @PutMapping
    public ResponseEntity<Void> updateThermostat(Authentication authentication, @Valid @RequestBody ThermostatDTO thermostat) {
        var isUpdated = service.updateThermostat(authentication.getName(), thermostat);
        return isUpdated ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/temp")
    public ThermostatTemperatureResponse updateTemperature(@Valid @RequestBody TemperatureRequest thermostat) {
        String name = thermostat.name();
        var state = service.updateThermostat(name, thermostat.actualTemp()) ? CRITICAL : NORMAL;
        log.info("Thermostat state: {}", state);
        return new ThermostatTemperatureResponse(name, state);
    }
}
