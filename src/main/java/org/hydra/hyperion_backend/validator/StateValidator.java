package org.hydra.hyperion_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hydra.hyperion_backend.annotation.ValidState;
import org.hydra.hyperion_backend.config.ValidatorConfig;
import org.hydra.hyperion_backend.util.AnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class StateValidator implements ConstraintValidator<ValidState, String> {
    @Autowired
    private ValidatorConfig validatorConfig;

    List<String> states;

    @Override
    public void initialize(ValidState constraintAnnotation) {
        try {
            System.out.println("initialize StateValidator");
            String type = constraintAnnotation.type();

            Field field = ValidatorConfig.ValidState.class.getDeclaredField(type);
            field.setAccessible(true);
            states = (List<String>) field.get(validatorConfig.getValidState());

            AnnotationUtil.updateAnnotationValue(
                    constraintAnnotation,
                    "message",
                    "只能是枚举常量" + states.toString()
            );
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.isEmpty() || states.contains(s);
    }
}
