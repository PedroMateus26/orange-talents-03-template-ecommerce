package com.pedromateus.zupacadey.MercadoLivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Instant createdAt;

    public Usuario(@Email @NotBlank String email, @NotBlank String senha) {

        Assert.isTrue(StringUtils.hasLength(email),"O email não deve ser nulo");
        Assert.isTrue(StringUtils.hasLength(senha), "A senha não deve ser nula");
        Assert.isTrue(senha.length()>=6,"A senha deve ter no mínimo 6 caracterres");

        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @PrePersist
    public void prePersist(){
        this.createdAt=Instant.now();
    }
}
