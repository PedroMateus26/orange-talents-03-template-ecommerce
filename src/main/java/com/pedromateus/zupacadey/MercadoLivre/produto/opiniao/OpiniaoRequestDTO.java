package com.pedromateus.zupacadey.MercadoLivre.produto.opiniao;

import com.pedromateus.zupacadey.MercadoLivre.produto.Produto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;

import javax.validation.constraints.*;

public class OpiniaoRequestDTO {

    @Min(value = 1,message = "A nota deve ser no mínimo 1")
    @Max(value = 5, message = "a nota deve ser no máximo 5")
    @NotNull(message = "A nota não pode ser nula")
    private Integer nota;
    @NotNull(message = "O título não pode ser nulo")
    @NotBlank(message = "O título não pode estar branco")
    private String titulo;
    @NotNull(message = "A descrção não pode ser nula")
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequestDTO() {
    }

    public OpiniaoRequestDTO(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}
