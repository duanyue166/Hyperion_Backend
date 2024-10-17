package org.hydra.hyperion_backend.util;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.annotation.ValidState;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/10/17
 * description：
 */
public class ValidationUtil {
    static public String getValidationErrorMessage(MethodArgumentNotValidException e) {
        try {
            FieldError filedError = e.getBindingResult().getFieldError();
            Field source = ObjectError.class.getDeclaredField("source");
            source.setAccessible(true);
            var constraintViolation = (ConstraintViolationImpl) source.get(filedError);
            Annotation anno = constraintViolation.getConstraintDescriptor().getAnnotation();
            if (anno instanceof ValidState || anno instanceof ValidRegexp) {
                Method messageMethod = anno.getClass().getMethod("message");
                return (String) messageMethod.invoke(anno);
            } else if (filedError != null) {
                return filedError.getDefaultMessage();
            } else {
                return "未知的校验错误";
            }
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }

    }
}
