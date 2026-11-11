package tcc.sagsa.models.dtos;

import tcc.sagsa.models.CriterioAvaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioAvaliacaoDTO {

    private Long id;

    @NotNull(message = "ID do instrumento é obrigatório")
    private Long instrumentoId;

    @NotNull(message = "ID da capacidade é obrigatório")
    private Long capacidadeId;

    @NotBlank(message = "Descrição do critério é obrigatória")
    private String descricaoCriterio;

    private BigDecimal prioridadePeso = BigDecimal.valueOf(1.00);

    public CriterioAvaliacaoDTO(CriterioAvaliacao entity) {
        this.id = entity.getId();
        this.descricaoCriterio = entity.getDescricaoCriterio();
        this.prioridadePeso = entity.getPrioridadePeso();
        if (entity.getInstrumento() != null) {
            this.instrumentoId = entity.getInstrumento().getId();
        }
        if (entity.getCapacidade() != null) {
            this.capacidadeId = entity.getCapacidade().getId();
        }
    }
}