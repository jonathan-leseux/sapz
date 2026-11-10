package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "situacoes_aprendizagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacaoAprendizagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Usuario instrutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column(name = "tipo_estrategia", nullable = false, length = 50)
    private String tipoEstrategia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contextualizacao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String desafio;

    @Column(name = "resultados_esperados", nullable = false, columnDefinition = "TEXT")
    private String resultadosEsperados;

    @Column(name = "carga_horaria_sa", nullable = false)
    private Integer cargaHorariaSa;

    @Column(nullable = false)
    private Boolean compartilhada = false;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(
        name = "sapz_capacidades",
        joinColumns = @JoinColumn(name = "situacao_aprendizagem_id"),
        inverseJoinColumns = @JoinColumn(name = "capacidade_id")
    )
    @Builder.Default
    private Set<Capacidade> capacidades = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "sapz_conhecimentos",
        joinColumns = @JoinColumn(name = "situacao_aprendizagem_id"),
        inverseJoinColumns = @JoinColumn(name = "conhecimento_id")
    )
    @Builder.Default
    private Set<Conhecimento> conhecimentos = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}