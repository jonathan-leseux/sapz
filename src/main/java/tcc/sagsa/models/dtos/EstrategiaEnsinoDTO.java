package tcc.sagsa.models.dtos;

import tcc.sagsa.models.EstrategiaEnsino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstrategiaEnsinoDTO {

    private Long id;

    @NotNull(message = "ID da Situação de Aprendizagem é obrigatório")
    private Long situacaoAprendizagemId;

    @NotBlank(message = "Nome da estratégia é obrigatório")
    private String nomeEstrategia;

    private String descricaoAtividade;

    public EstrategiaEnsinoDTO(EstrategiaEnsino entity) {
        this.id = entity.getId();
        this.nomeEstrategia = entity.getNomeEstrategia();
        this.descricaoAtividade = entity.getDescricaoAtividade();
        if (entity.getSituacaoAprendizagem() != null) {
            this.situacaoAprendizagemId = entity.getSituacaoAprendizagem().getId();
        }
    }
}

