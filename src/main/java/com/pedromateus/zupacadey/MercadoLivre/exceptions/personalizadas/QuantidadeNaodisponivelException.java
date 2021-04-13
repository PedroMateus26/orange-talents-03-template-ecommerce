package com.pedromateus.zupacadey.MercadoLivre.exceptions.personalizadas;

public class QuantidadeNaodisponivelException extends RuntimeException{
    public QuantidadeNaodisponivelException(String message) {
        super(message);
    }
}
