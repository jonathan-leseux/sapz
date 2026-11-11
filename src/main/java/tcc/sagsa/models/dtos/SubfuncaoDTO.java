package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Subfuncao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubfuncaoDTO {

    private Long id;

    @NotNull(message = "ID da função é obrigatório")
    private Long funcaoId;

    @NotBlank(message = "Código da subfunção é obrigatório")
    private String codigoSubfuncao;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    public SubfuncaoDTO(Subfuncao entity) {
        this.id = entity.getId();
        this.codigoSubfuncao = entity.getCodigoSubfuncao();
        this.descricao = entity.getDescricao();
        if (entity.getFuncao() != null) {
            this.funcaoId = entity.getFuncao().getId();
        }
    }
}
