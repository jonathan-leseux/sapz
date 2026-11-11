package tcc.sagsa.models.dtos;

import tcc.sagsa.models.PadraoDesempenho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadraoDesempenhoDTO {

    private Long id;

    @NotNull(message = "ID da subfunção é obrigatório")
    private Long subfuncaoId;

    @NotBlank(message = "Descrição no gerúndio é obrigatória")
    private String descricao;

    public PadraoDesempenhoDTO(PadraoDesempenho entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        if (entity.getSubfuncao() != null) {
            this.subfuncaoId = entity.getSubfuncao().getId();
        }
    }
}
