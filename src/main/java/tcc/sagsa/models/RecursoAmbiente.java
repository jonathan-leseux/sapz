package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sapz_recursos_ambientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoAmbiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "ambiente_pedagogico", nullable = false, length = 150)
    private String ambientePedagogico;

    @Column(name = "recursos_didaticos", nullable = false, columnDefinition = "TEXT")
    private String recursosDidaticos;
}
