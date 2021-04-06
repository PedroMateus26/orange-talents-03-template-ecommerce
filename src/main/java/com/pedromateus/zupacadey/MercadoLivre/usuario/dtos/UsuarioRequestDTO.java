package com.pedromateus.zupacadey.MercadoLivre.usuario.dtos;

import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import com.pedromateus.zupacadey.MercadoLivre.validations.UniqueValueValid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequestDTO {

    @UniqueValueValid(domainValue = Usuario.class, field="email")
    @NotBlank(message = "O email não pode ser nulo ou vazio")
    @Email(message = "O email deve ter formato válido")
    private String email;
    @NotBlank(message = "A sehha não pode ser nula ou vazia")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    public UsuarioRequestDTO(@NotBlank @Email String email, @NotBlank @Min(6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converteParaEntidade() {
        return new Usuario(email, this.senha);
    }
}
