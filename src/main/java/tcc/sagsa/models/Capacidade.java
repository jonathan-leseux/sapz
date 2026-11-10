package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "capacidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Capacidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    private UnidadeCurricular unidadeCurricular;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false, length = 30)
    private String dominio;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
}
