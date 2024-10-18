package org.hydra.hyperion_backend.annotation;

import jakarta.validation.Constraint;
import org.hydra.hyperion_backend.validator.StateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = StateValidator.class)
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface ValidState {
    String type();

    String message() default "ValidState: default message";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
