package com.uil.big_event.validation;

import com.uil.big_event.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) {
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
