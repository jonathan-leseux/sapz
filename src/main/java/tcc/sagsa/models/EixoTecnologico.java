package tcc.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eixos_tecnologicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EixoTecnologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;
}