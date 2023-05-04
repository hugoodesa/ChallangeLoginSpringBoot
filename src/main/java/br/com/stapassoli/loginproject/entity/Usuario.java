package br.com.stapassoli.loginproject.entity;

import br.com.stapassoli.loginproject.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "usuario_teste")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements ToDTO<UsuarioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;

    private Integer numeroTentativasFalhas;

    public Integer getLimiteTentativas(){
        return 3;
    }

    public boolean isSenhaValida(String senha) {
        if(!this.senha.equals(senha)){
            incrementarnumeroTentativasFalhas();
            return false;
        };

        return true;
    }

    public void incrementarnumeroTentativasFalhas () {
        ++this.numeroTentativasFalhas;
    }

    public boolean isBloqueado() {
        return this.numeroTentativasFalhas >= this.getLimiteTentativas();
    }

    @Override
    public UsuarioDTO convertToDTO() {
        return UsuarioDTO
                .builder()
                .login(this.login)
                .senha(this.senha)
                .build();
    }
}



