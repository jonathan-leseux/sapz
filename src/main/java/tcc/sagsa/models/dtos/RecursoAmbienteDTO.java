package tcc.sagsa.models.dtos;

import tcc.sagsa.models.RecursoAmbiente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoAmbienteDTO {

    private Long id;

    @NotNull(message = "ID da Situação de Aprendizagem é obrigatório")
    private Long situacaoAprendizagemId;

    @NotBlank(message = "Ambiente pedagógico é obrigatório")
    private String ambientePedagogico;

    @NotBlank(message = "Recursos didáticos são obrigatórios")
    private String recursosDidaticos;

    public RecursoAmbienteDTO(RecursoAmbiente entity) {
        this.id = entity.getId();
        this.ambientePedagogico = entity.getAmbientePedagogico();
        this.recursosDidaticos = entity.getRecursosDidaticos();
        if (entity.getSituacaoAprendizagem() != null) {
            this.situacaoAprendizagemId = entity.getSituacaoAprendizagem().getId();
        }
    }
}
