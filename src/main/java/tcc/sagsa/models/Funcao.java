package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_curso_id", nullable = false)
    private PlanoCurso planoCurso;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
}