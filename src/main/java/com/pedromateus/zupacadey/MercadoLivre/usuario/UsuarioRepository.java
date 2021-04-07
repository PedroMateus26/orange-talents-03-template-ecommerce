package com.pedromateus.zupacadey.MercadoLivre.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   Optional<Usuario> findByEmail(String s);
}
