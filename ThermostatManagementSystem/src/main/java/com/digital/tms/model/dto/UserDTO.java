package com.digital.tms.model.dto;

import com.digital.tms.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    @Size.List({
            @Size(max = 20, message = "maxTemp size reached"),
            @Size(min = 3, message = "can not be less then 3")
    })
    private String firstname;
    @NotNull
    @Size.List({
            @Size(max = 20, message = "maxTemp size reached"),
            @Size(min = 3, message = "can not be less then 3")
    })
    private String lastname;
    @Email
    @Size(max = 50, message = "maxTemp size reached")
    private String email;
}
