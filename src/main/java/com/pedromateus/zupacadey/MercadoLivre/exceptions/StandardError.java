package com.pedromateus.zupacadey.MercadoLivre.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StandardError {
    private Instant timestamp;
    private Integer status;
    private List<FieldErrors> errors=new ArrayList<>();

    public StandardError(Instant timestamp, Integer status) {
        this.timestamp = timestamp;
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public List<FieldErrors> getErrors() {
        return errors;
    }
}
