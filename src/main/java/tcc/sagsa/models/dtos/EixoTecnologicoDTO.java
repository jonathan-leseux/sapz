package tcc.sagsa.models.dtos;

import tcc.sagsa.models.EixoTecnologico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EixoTecnologicoDTO {

    private Long id;

    @NotBlank(message = "Nome do eixo tecnológico é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    public EixoTecnologicoDTO(EixoTecnologico entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
