package com.mrthinkj.kythucac.annotation;

import com.mrthinkj.kythucac.modelDTO.user.AccountRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        AccountRegisterDTO accountRegisterDTO = (AccountRegisterDTO) obj;
        return accountRegisterDTO.getPassword().equals(accountRegisterDTO.getMatchingPassword());
    }
}
