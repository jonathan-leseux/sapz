package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {

    private Long id;
    private Long areaTecnologicaId;
    private String areaTecnologicaNome;

    @NotBlank(message = "Nome do curso é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    private String nome;

    @NotBlank(message = "Tipo do curso é obrigatório")
    @Size(max = 50, message = "Tipo do curso deve ter no máximo 50 caracteres")
    private String tipoCurso;

    private Boolean ativo = true;

    public CursoDTO(Curso entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.tipoCurso = entity.getTipoCurso();
        this.ativo = entity.getAtivo();
        if (entity.getAreaTecnologica() != null) {
            this.areaTecnologicaId = entity.getAreaTecnologica().getId();
            this.areaTecnologicaNome = entity.getAreaTecnologica().getNome();
        }
    }
}