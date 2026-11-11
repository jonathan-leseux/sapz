package tcc.sagsa.models.dtos;

import tcc.sagsa.models.HistoricoPdf;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoPdfResponseDTO {

    private Long id;
    private Long situacaoAprendizagemId;
    private String situacaoAprendizagemTitulo;
    private String versao;
    private String caminhoArquivoPdf;
    private String hashConteudo;
    private LocalDateTime dataGeracao;
    private Long criadoPorUsuarioId;
    private String criadoPorUsuarioNome;

    public HistoricoPdfResponseDTO(HistoricoPdf entity) {
        this.id = entity.getId();
        this.versao = entity.getVersao();
        this.caminhoArquivoPdf = entity.getCaminhoArquivoPdf();
        this.hashConteudo = entity.getHashConteudo();
        this.dataGeracao = entity.getDataGeracao();
        if (entity.getSituacaoAprendizagem() != null) {
            this.situacaoAprendizagemId = entity.getSituacaoAprendizagem().getId();
            this.situacaoAprendizagemTitulo = entity.getSituacaoAprendizagem().getTitulo();
        }
        if (entity.getCriadoPorUsuario() != null) {
            this.criadoPorUsuarioId = entity.getCriadoPorUsuario().getId();
            this.criadoPorUsuarioNome = entity.getCriadoPorUsuario().getNome();
        }
    }
}