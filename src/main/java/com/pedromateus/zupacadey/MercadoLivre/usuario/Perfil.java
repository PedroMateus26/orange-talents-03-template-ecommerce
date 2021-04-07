package com.pedromateus.zupacadey.MercadoLivre.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String perfil;

    public Perfil() {
    }

    public Perfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public String getPerfil() {
        return perfil;
    }
}
