package tcc.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoPdfDTO {

    @NotNull(message = "ID da Situação de Aprendizagem é obrigatório")
    private Long situacaoAprendizagemId;

    private String versao = "1.0";

    @NotBlank(message = "Caminho do arquivo PDF é obrigatório")
    private String caminhoArquivoPdf;

    private String hashConteudo;
}