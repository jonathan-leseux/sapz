package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sapz_criterio_rubricas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioRubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterio_id", nullable = false)
    private CriterioAvaliacao criterio;

    @Column(nullable = false)
    private Integer nivel;

    @Column(name = "descricao_rubrica", nullable = false, columnDefinition = "TEXT")
    private String descricaoRubrica;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal pontuacao = BigDecimal.ZERO;
}
