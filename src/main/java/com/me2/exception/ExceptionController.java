package com.me2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ErrorHandler.class)
    public ResponseEntity<ErrorMessage> NotFoundException(ErrorHandler e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getError()));
    }
}