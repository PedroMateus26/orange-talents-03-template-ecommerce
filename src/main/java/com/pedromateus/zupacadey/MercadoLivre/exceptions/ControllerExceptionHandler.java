package com.pedromateus.zupacadey.MercadoLivre.exceptions;

import com.pedromateus.zupacadey.MercadoLivre.exceptions.personalizadas.FormaPamentoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotFoundException(MethodArgumentNotValidException e, HttpServletRequest http){
        ValidationError error=new ValidationError(Instant.now(),HttpStatus.BAD_REQUEST.value(),e.getFieldError().getDefaultMessage(), http.getRequestURI());

        for(FieldError f: e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(new FieldErrors(f.getField(),f.getDefaultMessage()));
        }
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> EntityNotFoundException(EntityNotFoundException e, HttpServletRequest http){
        StandardError error=new ValidationError(Instant.now(),HttpStatus.BAD_REQUEST.value(),e.getMessage(), http.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> BindException(BindException e, HttpServletRequest http){
        StandardError error=new StandardError(Instant.now(),HttpStatus.BAD_REQUEST.value(),e.getFieldError().getDefaultMessage(), http.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(FormaPamentoInvalidaException.class)
    public ResponseEntity<?> FormaPamentoInvalidaException(FormaPamentoInvalidaException e, HttpServletRequest http){
        StandardError error=new StandardError(Instant.now(),HttpStatus.BAD_REQUEST.value(),e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
