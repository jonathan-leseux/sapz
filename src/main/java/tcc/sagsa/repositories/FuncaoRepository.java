package tcc.sagsa.repositories;

import tcc.sagsa.models.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
    List<Funcao> findByPlanoCursoIdOrderByNumeroAsc(Long planoCursoId);
}