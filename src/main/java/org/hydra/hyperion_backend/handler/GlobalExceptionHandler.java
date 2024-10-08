package org.hydra.hyperion_backend.handler;

import org.hydra.hyperion_backend.pojo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class})
    public Result handleAccessDeniedException(Exception e) throws Exception {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        if (e == null || e.getMessage().isEmpty())
            return Result.error("unknown error");
        System.out.println("received exception: " + e.getMessage());
        System.out.println(e.getMessage());
        return Result.error(e.getMessage());
    }
}
