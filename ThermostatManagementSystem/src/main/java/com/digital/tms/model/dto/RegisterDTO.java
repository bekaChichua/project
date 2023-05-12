package com.digital.tms.model.dto;

import com.digital.tms.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PasswordMatches
@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO extends UserDTO {
        @NotNull
        @Size.List({
                @Size(max = 20, message = "maxTemp size reached"),
                @Size(min = 3, message = "can not be less then 3")
        })
        private String password;
        private String matchingPassword;
}
