package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Capacidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapacidadeDTO {

    private Long id;

    @NotNull(message = "ID da unidade curricular é obrigatório")
    private Long unidadeCurricularId;

    @NotBlank(message = "Tipo é obrigatório (BASICA, TECNICA, SOCIOEMOCIONAL)")
    private String tipo;

    @NotBlank(message = "Domínio é obrigatório (COGNITIVO, PSICOMOTOR, AFETIVO)")
    private String dominio;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    public CapacidadeDTO(Capacidade entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.dominio = entity.getDominio();
        this.descricao = entity.getDescricao();
        if (entity.getUnidadeCurricular() != null) {
            this.unidadeCurricularId = entity.getUnidadeCurricular().getId();
        }
    }
}

