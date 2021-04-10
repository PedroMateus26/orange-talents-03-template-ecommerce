package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public class PerguntasRequestDTO {

    private String titulo;

    public PerguntasRequestDTO(String titulo, Instant date) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }


}
