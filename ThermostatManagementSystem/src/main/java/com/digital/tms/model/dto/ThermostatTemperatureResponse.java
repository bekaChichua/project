package com.digital.tms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatTemperatureResponse {
    private String name;
    private ThermostatState state;

    public enum ThermostatState {
        CRITICAL,
        NORMAL
    }
}
