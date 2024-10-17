package org.hydra.hyperion_backend.handler;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.annotation.ValidState;
import org.hydra.hyperion_backend.pojo.Result;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        if (e == null || e.getMessage().isEmpty())
            return Result.error("unknown error");
        System.out.println("received exception: " + e.getMessage());
        System.out.println(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public Result handleMybatisException(MyBatisSystemException e) {
        return Result.error(e.getCause().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        try {
            String fieldName = Objects.requireNonNull(e.getBindingResult().getFieldError()).getField();
            FieldError filedError = e.getBindingResult().getFieldError();
            Field source = ObjectError.class.getDeclaredField("source");
            source.setAccessible(true);
            var constraintViolation = (ConstraintViolationImpl) source.get(filedError);
            Annotation anno = constraintViolation.getConstraintDescriptor().getAnnotation();
            if (anno instanceof ValidState || anno instanceof ValidRegexp) {
                Method messageMethod = anno.getClass().getMethod("message");
                String errorMessage = (String) messageMethod.invoke(anno);
                return Result.error(-101, fieldName + "校验失败，" +errorMessage);
            } else
                return Result.error(-101, fieldName + "校验失败，" + e.getBindingResult().getFieldError().getDefaultMessage());
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }
}
