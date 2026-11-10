package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sapz_criterios_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrumento_id", nullable = false)
    private InstrumentoAvaliacao instrumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capacidade_id", nullable = false)
    private Capacidade capacidade;

    @Column(name = "descricao_criterio", nullable = false, columnDefinition = "TEXT")
    private String descricaoCriterio;

    @Column(name = "prioridade_peso", precision = 5, scale = 2)
    private BigDecimal prioridadePeso = BigDecimal.valueOf(1.00);
}
