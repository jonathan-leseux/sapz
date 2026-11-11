package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Modulo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuloDTO {

    private Long id;

    @NotNull(message = "ID do plano de curso é obrigatório")
    private Long planoCursoId;

    @NotBlank(message = "Nome do módulo é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo do módulo é obrigatório")
    private String tipoModulo;

    private Integer ordem = 1;

    @NotNull(message = "Carga horária é obrigatória")
    @Positive(message = "Carga horária deve ser positiva")
    private Integer cargaHoraria;

    public ModuloDTO(Modulo entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.tipoModulo = entity.getTipoModulo();
        this.ordem = entity.getOrdem();
        this.cargaHoraria = entity.getCargaHoraria();
        if (entity.getPlanoCurso() != null) {
            this.planoCursoId = entity.getPlanoCurso().getId();
        }
    }
}


