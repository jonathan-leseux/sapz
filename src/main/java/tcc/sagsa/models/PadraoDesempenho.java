package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "padroes_desempenho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadraoDesempenho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subfuncao_id", nullable = false)
    private Subfuncao subfuncao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
}