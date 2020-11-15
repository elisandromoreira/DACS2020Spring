package br.univille.dacs2020.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.univille.dacs2020.repository.UsuarioRepository;
import br.univille.dacs2020.model.Usuario;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository service; 
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario buscaUsuarioSenha(String nomeUsuario, String senhaUsuario){
        return service.findByUsuarioAndSenha(nomeUsuario, senhaUsuario);
    }
    public Usuario buscaUsuario(String nomeUsuario){
        return service.findByUsuario(nomeUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = service.findByUsuario(nomeUsuario);
        return new User(usuario.getUsuario(),usuario.getSenha(), new ArrayList<>());
    }

    public void save(Usuario usuario){
        if (usuario.getSenha().length() != 0)
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        service.save(usuario);
    }
    
    
}

