package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sapz_instrumentos_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstrumentoAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "nome_instrumento", nullable = false, length = 100)
    private String nomeInstrumento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tipo_metodo", nullable = false, length = 30)
    private String tipoMetodo = "GRADUAL";
}
