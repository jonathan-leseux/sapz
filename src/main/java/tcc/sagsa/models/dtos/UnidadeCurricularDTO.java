package tcc.sagsa.models.dtos;

import tcc.sagsa.models.UnidadeCurricular;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadeCurricularDTO {

    private Long id;

    @NotNull(message = "ID do módulo é obrigatório")
    private Long moduloId;

    private String moduloNome;

    @NotBlank(message = "Nome da unidade curricular é obrigatório")
    private String nome;

    @NotNull(message = "Carga horária é obrigatória")
    @Positive(message = "Carga horária deve ser positiva")
    private Integer cargaHoraria;

    private String objetivoGeral;
    private Integer ordem = 1;

    public UnidadeCurricularDTO(UnidadeCurricular entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cargaHoraria = entity.getCargaHoraria();
        this.objetivoGeral = entity.getObjetivoGeral();
        this.ordem = entity.getOrdem();
        if (entity.getModulo() != null) {
            this.moduloId = entity.getModulo().getId();
            this.moduloNome = entity.getModulo().getNome();
        }
    }
}
