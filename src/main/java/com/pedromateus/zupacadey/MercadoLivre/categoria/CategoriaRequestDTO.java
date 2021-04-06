package com.pedromateus.zupacadey.MercadoLivre.categoria;

import com.pedromateus.zupacadey.MercadoLivre.validations.UniqueValueValid;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class CategoriaRequestDTO {

    @NotBlank(message = "A categoria não pode estar nula ou em branco")
    @UniqueValueValid(domainValue = Categoria.class,field = "nome")
    private String nome;

    public CategoriaRequestDTO() {
    }

    public CategoriaRequestDTO(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria convertToModel() {
        return new Categoria(this.nome);
    }
}
