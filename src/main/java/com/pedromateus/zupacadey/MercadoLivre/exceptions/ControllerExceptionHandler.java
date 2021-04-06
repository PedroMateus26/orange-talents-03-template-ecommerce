package com.pedromateus.zupacadey.MercadoLivre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotFoundException(MethodArgumentNotValidException e){
        StandardError error=new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value());

        for(FieldError f: e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(new FieldErrors(f.getField(),f.getDefaultMessage()));
        }
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
