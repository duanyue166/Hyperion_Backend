package org.hydra.hyperion_backend;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hydra.hyperion_backend.annotation.ValidState;
import org.hydra.hyperion_backend.util.AnnotationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidatorTests {
    @Test
    void testAnno() throws NoSuchFieldException {
        ValidState validState = MyClass.class.getDeclaredField("state").getAnnotation(ValidState.class);
        AnnotationUtil.updateAnnotationValue(validState, "message", "新的非法枚举常量消息");
        assertEquals("新的非法枚举常量消息", validState.message());
    }
}

class MyClass {
    @ValidState(states = {"ACTIVE", "INACTIVE"})
    private String state;
}