package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subfuncoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subfuncao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private Funcao funcao;

    @Column(name = "codigo_subfuncao", nullable = false, length = 20)
    private String codigoSubfuncao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
}
