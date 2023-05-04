package br.com.stapassoli.loginproject.model;

import br.com.stapassoli.loginproject.dto.UsuarioDTO;
import br.com.stapassoli.loginproject.entity.Usuario;
import br.com.stapassoli.loginproject.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Login {

    @Autowired
    private UsuarioService service;

    public ResponseEntity isUsuarioValido (UsuarioDTO usuarioDTO){
        Usuario usuario = this.service.buscarUsuarioPorLogin(usuarioDTO);

        if(usuario.isBloqueado()){
            return ResponseEntity.badRequest().body("Usuário se encontra bloqueado ");
        }

        if(!usuario.isSenhaValida(usuarioDTO.getSenha())){
            this.service.atualizarNumeroDeTentativas(usuario);
            return ResponseEntity.badRequest().body("Usuário inválido tem mais " + (usuario.getLimiteTentativas() - usuario.getNumeroTentativasFalhas()) +" tentativas ");
        }

        return ResponseEntity.ok("usuário logado com sucesso !");
    }

}
