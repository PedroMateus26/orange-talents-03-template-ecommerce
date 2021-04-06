package com.pedromateus.zupacadey.MercadoLivre.usuario.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;

public class UsuarioResponseDTO {

    @JsonProperty("email")
    private String email;

    public UsuarioResponseDTO(Usuario entity) {
        this.email = entity.getEmail();
    }

}
