package org.hydra.hyperion_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.config.ValidatorConfig;
import org.hydra.hyperion_backend.util.AnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class RegexpValidator implements ConstraintValidator<ValidRegexp, String> {
    @Autowired
    ValidatorConfig config;

    private String regexp;

    @Override
    public void initialize(ValidRegexp constraintAnno) {
        try {
            var type = constraintAnno.type();
            var field = config.getClass().getField(type);
            var b = (ValidatorConfig.BaseValidator) field.get(config);
            regexp = b.getRegexp();
            AnnotationUtil.updateAnnotationValue(
                    constraintAnno,
                    "message",
                    b.getMessage()
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
