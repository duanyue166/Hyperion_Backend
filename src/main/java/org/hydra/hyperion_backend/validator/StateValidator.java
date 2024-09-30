package org.hydra.hyperion_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hydra.hyperion_backend.annotation.ValidState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class StateValidator implements ConstraintValidator<ValidState, String> {
    List<String> states;

    @Override
    public void initialize(ValidState constraintAnnotation) {
        try {
            Method method = constraintAnnotation.getClass().getMethod("states");
            var array = (String[]) method.invoke(constraintAnnotation);
            states = Arrays.asList(array);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.isEmpty() || states.contains(s);
    }
}
