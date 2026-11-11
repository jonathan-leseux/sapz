package tcc.sagsa.models.dtos;

import tcc.sagsa.models.CriterioRubrica;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioRubricaDTO {

    private Long id;

    @NotNull(message = "ID do critério é obrigatório")
    private Long criterioId;

    @NotNull(message = "Nível da rubrica é obrigatório (1, 2, 3 ou 4)")
    private Integer nivel;

    @NotBlank(message = "Descrição da rubrica é obrigatória")
    private String descricaoRubrica;

    @NotNull(message = "Pontuação é obrigatória")
    private BigDecimal pontuacao = BigDecimal.ZERO;

    public CriterioRubricaDTO(CriterioRubrica entity) {
        this.id = entity.getId();
        this.nivel = entity.getNivel();
        this.descricaoRubrica = entity.getDescricaoRubrica();
        this.pontuacao = entity.getPontuacao();
        if (entity.getCriterio() != null) {
            this.criterioId = entity.getCriterio().getId();
        }
    }
}