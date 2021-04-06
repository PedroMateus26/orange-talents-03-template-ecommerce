package com.pedromateus.zupacadey.MercadoLivre.exceptions;

public class FieldErrors {

    private String field;
    private String message;

    public FieldErrors(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
