package tcc.sagsa.repositories;

import tcc.sagsa.models.AreaTecnologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AreaTecnologicaRepository extends JpaRepository<AreaTecnologica, Long> {
    List<AreaTecnologica> findByEixoTecnologicoId(Long eixoId);
}