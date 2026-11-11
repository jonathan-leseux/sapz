package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Conhecimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConhecimentoDTO {

    private Long id;

    @NotNull(message = "ID da unidade curricular é obrigatório")
    private Long unidadeCurricularId;

    @NotBlank(message = "Tópico é obrigatório")
    private String topico;

    private String detalhamento;

    public ConhecimentoDTO(Conhecimento entity) {
        this.id = entity.getId();
        this.topico = entity.getTopico();
        this.detalhamento = entity.getDetalhamento();
        if (entity.getUnidadeCurricular() != null) {
            this.unidadeCurricularId = entity.getUnidadeCurricular().getId();
        }
    }
}
