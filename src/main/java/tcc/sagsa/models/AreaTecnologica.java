package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "areas_tecnologicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaTecnologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eixo_tecnologico_id", nullable = false)
    private EixoTecnologico eixoTecnologico;

    @Column(nullable = false, length = 100)
    private String nome;
}