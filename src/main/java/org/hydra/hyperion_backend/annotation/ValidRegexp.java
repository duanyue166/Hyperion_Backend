package org.hydra.hyperion_backend.annotation;

import jakarta.validation.Constraint;
import org.hydra.hyperion_backend.validator.RegexpValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = RegexpValidator.class)
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface ValidRegexp {
    String type();

    String message() default "invalid";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
