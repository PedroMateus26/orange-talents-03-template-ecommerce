package com.pedromateus.zupacadey.MercadoLivre.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
