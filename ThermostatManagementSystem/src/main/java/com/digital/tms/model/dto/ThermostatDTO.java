package com.digital.tms.model.dto;

import jakarta.validation.constraints.*;

public record ThermostatDTO(
        @NotEmpty String name,
        @Digits(integer = 3, fraction = 2) @NotNull Double maxTemp,
        @Digits(integer = 3, fraction = 2) Double actualTemp) {
}
