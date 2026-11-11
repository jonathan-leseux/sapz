package tcc.sagsa.repositories;

import tcc.sagsa.models.PlanoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanoCursoRepository extends JpaRepository<PlanoCurso, Long> {
    List<PlanoCurso> findByCursoId(Long cursoId);
}