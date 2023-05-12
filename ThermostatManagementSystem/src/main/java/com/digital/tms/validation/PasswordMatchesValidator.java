package com.digital.tms.validation;

import com.digital.tms.model.dto.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, RegisterDTO> {

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("matchingPassword").addConstraintViolation();
        if(user.getPassword() == null || user.getMatchingPassword() == null) return false;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
