package br.com.stapassoli.loginproject.service;

import br.com.stapassoli.loginproject.dto.UsuarioDTO;
import br.com.stapassoli.loginproject.entity.Usuario;
import br.com.stapassoli.loginproject.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario buscarUsuarioPorLogin(UsuarioDTO usuarioDTO) {

        Optional<Usuario> optinalUsuario = this.repository.findByLoginLike(usuarioDTO.getLogin());

        if (optinalUsuario.isPresent()) {
            return optinalUsuario.get();
        }

        throw new EntityNotFoundException("Usuario não foi encontrado");
    }

    public ResponseEntity<UsuarioDTO> cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.repository.save(usuarioDTO.convertToEntity());
        return ResponseEntity.ok(usuario.convertToDTO());
    }

    public void atualizarNumeroDeTentativas(Usuario usuario) {
        this.repository.save(usuario);
    }

}
