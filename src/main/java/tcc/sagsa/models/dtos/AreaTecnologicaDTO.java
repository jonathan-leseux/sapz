package tcc.sagsa.models.dtos;

import tcc.sagsa.models.AreaTecnologica;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaTecnologicaDTO {

    private Long id;

    @NotNull(message = "ID do Eixo Tecnológico é obrigatório")
    private Long eixoTecnologicoId;

    private String eixoTecnologicoNome;

    @NotBlank(message = "Nome da área tecnológica é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    public AreaTecnologicaDTO(AreaTecnologica entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        if (entity.getEixoTecnologico() != null) {
            this.eixoTecnologicoId = entity.getEixoTecnologico().getId();
            this.eixoTecnologicoNome = entity.getEixoTecnologico().getNome();
        }
    }
}