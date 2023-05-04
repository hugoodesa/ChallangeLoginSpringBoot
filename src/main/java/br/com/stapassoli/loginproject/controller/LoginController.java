package br.com.stapassoli.loginproject.controller;

import br.com.stapassoli.loginproject.dto.UsuarioDTO;
import br.com.stapassoli.loginproject.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    Login login;

    @PostMapping
    public ResponseEntity<String> logar(@RequestBody UsuarioDTO usuarioDTO){
        return login.isUsuarioValido(usuarioDTO);
    }

}
