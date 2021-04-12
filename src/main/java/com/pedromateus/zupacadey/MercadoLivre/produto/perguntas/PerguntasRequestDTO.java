package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedromateus.zupacadey.MercadoLivre.produto.Produto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public class PerguntasRequestDTO {


    private String titulo;

    public PerguntasRequestDTO() {
    }

    public PerguntasRequestDTO(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta convertToPergunta(Usuario usuario, Produto produto){
        return new Pergunta(
                this.titulo,
                usuario,
                produto
        );
    }

}
