package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conhecimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    private UnidadeCurricular unidadeCurricular;

    @Column(nullable = false, length = 200)
    private String topico;

    @Column(columnDefinition = "TEXT")
    private String detalhamento;
}