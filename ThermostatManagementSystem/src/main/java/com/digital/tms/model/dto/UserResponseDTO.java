package com.digital.tms.model.dto;

import com.digital.tms.model.entity.ThermostatEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends UserDTO {
    private List<ThermostatDTO> thermostats = new ArrayList<>();
}
