package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Escola;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EscolaDTO {

    private Long id;

    @NotBlank(message = "Nome da escola é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    private String nome;

    @Size(max = 20, message = "Código da unidade deve ter no máximo 20 caracteres")
    private String codigoUnidade;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve possuir exatamente 2 caracteres (UF)")
    private String estado = "RS";

    private Boolean ativo = true;

    public EscolaDTO(Escola escola) {
        this.id = escola.getId();
        this.nome = escola.getNome();
        this.codigoUnidade = escola.getCodigoUnidade();
        this.cidade = escola.getCidade();
        this.estado = escola.getEstado();
        this.ativo = escola.getAtivo();
    }
}
