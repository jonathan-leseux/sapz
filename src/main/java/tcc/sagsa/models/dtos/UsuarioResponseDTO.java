package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String perfilNome;
    private Boolean ativo;
    private LocalDateTime dataCriacao;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfilNome = usuario.getPerfil() != null ? usuario.getPerfil().getNome() : null;
        this.ativo = usuario.getAtivo();
        this.dataCriacao = usuario.getDataCriacao();
    }
}

