package com.pedromateus.zupacadey.MercadoLivre.usuario;

import com.pedromateus.zupacadey.MercadoLivre.usuario.dtos.UsuarioRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.usuario.dtos.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

    private UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> insertUsuario(@RequestBody @Valid UsuarioRequestDTO requestDTO){
        Usuario usuario = requestDTO.converteParaEntidade();
        return ResponseEntity.ok().body(new UsuarioResponseDTO(repository.save(usuario)));
    }

    @GetMapping
    public String testeAutorizacao(){
        return "token válido, usuário autorizado";
    }
}
