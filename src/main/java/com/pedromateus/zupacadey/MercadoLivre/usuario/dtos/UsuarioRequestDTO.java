package com.pedromateus.zupacadey.MercadoLivre.usuario.dtos;

import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequestDTO(@NotBlank @Email String email, @NotBlank @Min(6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converteParaEntidade() {
        return new Usuario(email, this.senha);
    }
}
