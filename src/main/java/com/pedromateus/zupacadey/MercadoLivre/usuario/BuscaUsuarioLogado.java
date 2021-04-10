package com.pedromateus.zupacadey.MercadoLivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BuscaUsuarioLogado implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(s)
                .orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
        return usuario;
    }

    public Usuario usuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario= (Usuario) loadUserByUsername(authentication.getName());
        return usuario;
    }
}
