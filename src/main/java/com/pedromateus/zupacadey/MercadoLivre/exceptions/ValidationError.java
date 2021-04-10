package com.pedromateus.zupacadey.MercadoLivre.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldErrors> errors=new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public List<FieldErrors> getErrors() {
        return errors;
    }

    public void addError(String erro, String messagem){
        errors.add(new FieldErrors(erro,messagem));
    }


}
