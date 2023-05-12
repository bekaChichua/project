package com.digital.tms.service.iServicies;

import com.digital.tms.model.dto.ThermostatDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IThermostatService {

    List<ThermostatDTO> getAll();

    List<ThermostatDTO> getAll(String email);

    ThermostatDTO getThermostatBy(String email, String name);

    void addThermostat(String email, ThermostatDTO thermostat);

    void deleteThermostatBy(String email, String name);

    Boolean updateThermostat(String email, ThermostatDTO thermostat);

    Boolean updateThermostat(String name, Double temperature);
}
