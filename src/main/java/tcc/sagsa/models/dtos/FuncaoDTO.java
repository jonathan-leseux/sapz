package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Funcao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncaoDTO {

    private Long id;

    @NotNull(message = "ID do plano de curso é obrigatório")
    private Long planoCursoId;

    @NotNull(message = "Número da função é obrigatório")
    private Integer numero;

    @NotBlank(message = "Descrição da função é obrigatória")
    private String descricao;

    public FuncaoDTO(Funcao entity) {
        this.id = entity.getId();
        this.numero = entity.getNumero();
        this.descricao = entity.getDescricao();
        if (entity.getPlanoCurso() != null) {
            this.planoCursoId = entity.getPlanoCurso().getId();
        }
    }
}

