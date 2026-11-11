package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sapz_compartilhamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compartilhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_destinatario_id")
    private Usuario usuarioDestinatario;

    @Column(name = "tipo_compartilhamento", nullable = false, length = 30)
    private String tipoCompartilhamento;

    @Column(name = "data_compartilhamento", nullable = false, updatable = false)
    private LocalDateTime dataCompartilhamento;

    @PrePersist
    public void prePersist() {
        this.dataCompartilhamento = LocalDateTime.now();
    }
}
