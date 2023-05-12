package com.digital.tms.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

public record TemperatureRequest(@NotEmpty String name, @Digits(integer = 3, fraction = 2) double actualTemp) { }
