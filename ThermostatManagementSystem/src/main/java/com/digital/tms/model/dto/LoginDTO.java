package com.digital.tms.model.dto;


import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(@NotEmpty String email, @NotEmpty String password) {
}
