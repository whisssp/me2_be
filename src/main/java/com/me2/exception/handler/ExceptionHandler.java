package com.me2.exception.handler;

import com.me2.exception.CustomException;
import com.me2.exception.ErrorMessage;
import com.me2.exception.ResetTokenExpiredException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;

@RestControllerAdvice
@EnableWebMvc
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomException.class, ResetTokenExpiredException.class})
    public ResponseEntity<ErrorMessage> exception(Exception e) {
        ErrorMessage errorMessage = null;
        if (e instanceof CustomException) {
            e = (CustomException) e;
            errorMessage = new ErrorMessage(((CustomException) e).getError());
        } else if (e instanceof ResetTokenExpiredException) {
            e = (ResetTokenExpiredException) e;
            errorMessage = new ErrorMessage(((ResetTokenExpiredException) e).getError());
        }
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProblemDetail> handleException(Exception e) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("INTERNAL_SERVER_ERROR");
        problem.setDetail(e.getMessage());
        problem.setType(URI.create("/me2/error"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProblemDetail> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("NotFound");
        problem.setDetail("Cannot find resource.");
        problem.setType(URI.create("me2/error"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
}