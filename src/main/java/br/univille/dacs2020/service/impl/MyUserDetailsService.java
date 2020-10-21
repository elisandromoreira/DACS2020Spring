package br.univille.dacs2020.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univille.dacs2020.repository.UsuarioRepository;
import br.univille.dacs2020.model.Usuario;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository service; 

    public Usuario buscaUsuarioSenha(String nomeUsuario, String senhaUsuario){
        return service.findByUsuarioAndSenha(nomeUsuario, senhaUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = service.findByUsuario(nomeUsuario);
        return new User(usuario.getUsuario(),usuario.getSenha(), new ArrayList<>());
    }


}
