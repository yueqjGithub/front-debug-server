package com.example.front_debug_server.advice;

import com.example.front_debug_server.http.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResp<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return HttpResp.failed(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
