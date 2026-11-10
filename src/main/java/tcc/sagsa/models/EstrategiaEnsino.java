package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sapz_estrategias_ensino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstrategiaEnsino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "nome_estrategia", nullable = false, length = 100)
    private String nomeEstrategia;

    @Column(name = "descricao_atividade", columnDefinition = "TEXT")
    private String descricaoAtividade;
}