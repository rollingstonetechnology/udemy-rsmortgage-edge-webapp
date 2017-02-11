package com.rollingstone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rollingstone.persistence.model.RsMortgageUser;



public class RsMortgagePasswordMatchesValidator implements ConstraintValidator<RsMortgagePasswordMatches, Object> {

    @Override
    public void initialize(final RsMortgagePasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RsMortgageUser user = (RsMortgageUser) obj;
        return user.getPassword().equals(user.getPasswordConfirmation());
    }

}
