package org.hydra.hyperion_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.config.ValidatorConfig;
import org.hydra.hyperion_backend.util.AnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

public class RegexpValidator implements ConstraintValidator<ValidRegexp, String> {
    @Autowired
    ValidatorConfig config;

    private String regexp;

    @Override
    public void initialize(ValidRegexp constraintAnno) {
        try {
            String type = constraintAnno.type();
            Field field = ValidatorConfig.ValidRegexp.class.getDeclaredField(type);
            field.setAccessible(true);
            var valid = (ValidatorConfig.ValidRegexp.Base) field.get(config.getValidRegexp());
            regexp = valid.getRegexp();
            AnnotationUtil.updateAnnotationValue(
                    constraintAnno,
                    "message",
                    valid.getMessage()
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.matches(regexp);
    }
}
