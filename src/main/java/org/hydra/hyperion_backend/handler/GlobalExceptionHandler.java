package org.hydra.hyperion_backend.handler;

import org.hydra.hyperion_backend.pojo.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
