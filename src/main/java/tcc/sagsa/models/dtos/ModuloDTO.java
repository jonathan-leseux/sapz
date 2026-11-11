package tcc.sagsa.models.dtos;

import tcc.sagsa.models.PlanoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanoCursoDTO {

    private Long id;

    @NotNull(message = "ID do curso é obrigatório")
    private Long cursoId;

    private String cursoNome;

    @NotBlank(message = "Código do plano é obrigatório")
    private String codigoPlano;

    private String versao = "1.0";

    @NotNull(message = "Carga horária total é obrigatória")
    @Positive(message = "Carga horária deve ser positiva")
    private Integer cargaHorariaTotal;

    private LocalDate dataAprovacao;
    private Boolean ativo = true;

    public PlanoCursoDTO(PlanoCurso entity) {
        this.id = entity.getId();
        this.codigoPlano = entity.getCodigoPlano();
        this.versao = entity.getVersao();
        this.cargaHorariaTotal = entity.getCargaHorariaTotal();
        this.dataAprovacao = entity.getDataAprovacao();
        this.ativo = entity.getAtivo();
        if (entity.getCurso() != null) {
            this.cursoId = entity.getCurso().getId();
            this.cursoNome = entity.getCurso().getNome();
        }
    }
}



