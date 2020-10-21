package br.univille.dacs2020.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import br.univille.dacs2020.model.Usuario;
import br.univille.dacs2020.security.JWTUtil;
import br.univille.dacs2020.service.impl.MyUserDetailsService;
 
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationControllerAPI {
 
    @Autowired
    private MyUserDetailsService serviceMyUserDetail;
    @Autowired
    private JWTUtil serviceJWT;
 
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody Usuario usuario){
        Usuario usuarioValido = serviceMyUserDetail.buscaUsuarioSenha(usuario.getUsuario(), usuario.getSenha());
        UserDetails userDetails = serviceMyUserDetail.loadUserByUsername(usuarioValido.getUsuario());
        String token = serviceJWT.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}