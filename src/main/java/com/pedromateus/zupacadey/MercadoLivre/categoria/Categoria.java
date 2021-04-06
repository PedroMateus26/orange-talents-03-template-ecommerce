package com.pedromateus.zupacadey.MercadoLivre.categoria;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;

    public Categoria(@NotBlank String nome) {

        Assert.isTrue(StringUtils.hasLength(nome), "O nome não pode ser nulo");
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
