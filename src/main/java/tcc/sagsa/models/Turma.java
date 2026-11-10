package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_curso_id", nullable = false)
    private PlanoCurso planoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_id")
    private Usuario coordenador;

    @Column(name = "codigo_turma", nullable = false, unique = true, length = 50)
    private String codigoTurma;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Integer semestre;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private Boolean ativo = true;
}
