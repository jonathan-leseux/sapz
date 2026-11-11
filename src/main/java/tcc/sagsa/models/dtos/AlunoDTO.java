package tcc.sagsa.models.dtos;

import tcc.sagsa.models.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoDTO {

    private Long id;

    @NotBlank(message = "Nome do aluno é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 14, message = "CPF inválido")
    private String cpf;

    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    public AlunoDTO(Aluno entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.matricula = entity.getMatricula();
    }
}

