package br.com.stapassoli.loginproject.dto;

import br.com.stapassoli.loginproject.entity.Usuario;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements ToEntity<Usuario> {

    private String login;
    private String senha;

    @Override
    public Usuario convertToEntity() {
        return Usuario
                .builder()
                .login(this.login)
                .senha(this.senha)
                .numeroTentativasFalhas(0)
                .build();
    }
}
