package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sapz_historico_pdf")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(nullable = false, length = 20)
    private String versao = "1.0";

    @Column(name = "caminho_arquivo_pdf", nullable = false, length = 255)
    private String caminhoArquivoPdf;

    @Column(name = "hash_conteudo", length = 64)
    private String hashConteudo;

    @Column(name = "data_geracao", nullable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_usuario_id", nullable = false)
    private Usuario criadoPorUsuario;

    @PrePersist
    public void prePersist() {
        this.dataGeracao = LocalDateTime.now();
    }
}
