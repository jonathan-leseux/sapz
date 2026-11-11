package tcc.sagsa.models.dtos;


import tcc.sagsa.models.Compartilhamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompartilhamentoDTO {

    private Long id;

    @NotNull(message = "ID da Situação de Aprendizagem é obrigatório")
    private Long situacaoAprendizagemId;

    private Long escolaId;
    private Long cursoId;
    private Long usuarioDestinatarioId;

    @NotBlank(message = "Tipo de compartilhamento é obrigatório (ESCOLA, CURSO, USUARIO, GLOBAL)")
    private String tipoCompartilhamento;

    private LocalDateTime dataCompartilhamento;

    public CompartilhamentoDTO(Compartilhamento entity) {
        this.id = entity.getId();
        this.tipoCompartilhamento = entity.getTipoCompartilhamento();
        this.dataCompartilhamento = entity.getDataCompartilhamento();
        if (entity.getSituacaoAprendizagem() != null) {
            this.situacaoAprendizagemId = entity.getSituacaoAprendizagem().getId();
        }
        if (entity.getEscola() != null) this.escolaId = entity.getEscola().getId();
        if (entity.getCurso() != null) this.cursoId = entity.getCurso().getId();
        if (entity.getUsuarioDestinatario() != null) this.usuarioDestinatarioId = entity.getUsuarioDestinatario().getId();
    }
}