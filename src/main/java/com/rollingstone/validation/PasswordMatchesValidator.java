package com.rollingstone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rollingstone.persistence.model.RsMortgageUser;



public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RsMortgageUser user = (RsMortgageUser) obj;
        return user.getPassword().equals(user.getPasswordConfirmation());
    }

}
