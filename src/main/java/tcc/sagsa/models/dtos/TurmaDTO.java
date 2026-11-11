package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Turma;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurmaDTO {

    private Long id;

    @NotNull(message = "ID da escola é obrigatório")
    private Long escolaId;
    private String escolaNome;

    @NotNull(message = "ID do plano de curso é obrigatório")
    private Long planoCursoId;
    private String planoCursoCodigo;

    private Long coordenadorId;
    private String coordenadorNome;

    @NotBlank(message = "Código da turma é obrigatório")
    private String codigoTurma;

    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @NotNull(message = "Semestre é obrigatório")
    private Integer semestre;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDate dataFim;

    private Boolean ativo = true;

    public TurmaDTO(Turma entity) {
        this.id = entity.getId();
        this.codigoTurma = entity.getCodigoTurma();
        this.ano = entity.getAno();
        this.semestre = entity.getSemestre();
        this.dataInicio = entity.getDataInicio();
        this.dataFim = entity.getDataFim();
        this.ativo = entity.getAtivo();
        if (entity.getEscola() != null) {
            this.escolaId = entity.getEscola().getId();
            this.escolaNome = entity.getEscola().getNome();
        }
        if (entity.getPlanoCurso() != null) {
            this.planoCursoId = entity.getPlanoCurso().getId();
            this.planoCursoCodigo = entity.getPlanoCurso().getCodigoPlano();
        }
        if (entity.getCoordenador() != null) {
            this.coordenadorId = entity.getCoordenador().getId();
            this.coordenadorNome = entity.getCoordenador().getNome();
        }
    }
}



