package tcc.sagsa.repositories;

import tcc.sagsa.models.EixoTecnologico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EixoTecnologicoRepository extends JpaRepository<EixoTecnologico, Long> {}
