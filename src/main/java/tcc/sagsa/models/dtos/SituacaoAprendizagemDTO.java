package tcc.sagsa.models.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class SituacaoAprendizagemDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotNull(message = "Unidade Curricular é obrigatória")
    private Long unidadeCurricularId;

    private Long turmaId;

    @NotBlank(message = "Tipo de estratégia é obrigatório")
    private String tipoEstrategia;

    @NotBlank(message = "Contextualização é obrigatória")
    private String contextualizacao;

    @NotBlank(message = "Desafio é obrigatório")
    private String desafio;

    @NotBlank(message = "Resultados esperados são obrigatórios")
    private String resultadosEsperados;

    @NotNull(message = "Carga horária é obrigatória")
    private Integer cargaHorariaSa;

    private Boolean compartilhada = false;

    private Set<Long> capacidadesIds;
    private Set<Long> conhecimentosIds;
}
