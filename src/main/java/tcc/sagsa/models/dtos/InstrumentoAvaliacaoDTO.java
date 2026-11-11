package tcc.sagsa.models.dtos;


import tcc.sagsa.models.InstrumentoAvaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstrumentoAvaliacaoDTO {

    private Long id;

    @NotNull(message = "ID da Situação de Aprendizagem é obrigatório")
    private Long situacaoAprendizagemId;

    @NotBlank(message = "Nome do instrumento é obrigatório")
    private String nomeInstrumento;

    private String descricao;
    private String tipoMetodo = "GRADUAL";

    public InstrumentoAvaliacaoDTO(InstrumentoAvaliacao entity) {
        this.id = entity.getId();
        this.nomeInstrumento = entity.getNomeInstrumento();
        this.descricao = entity.getDescricao();
        this.tipoMetodo = entity.getTipoMetodo();
        if (entity.getSituacaoAprendizagem() != null) {
            this.situacaoAprendizagemId = entity.getSituacaoAprendizagem().getId();
        }
    }
}
