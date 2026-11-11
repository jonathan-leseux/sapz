package tcc.sagsa.repositories;


import tcc.sagsa.models.CriterioRubrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CriterioRubricaRepository extends JpaRepository<CriterioRubrica, Long> {
    List<CriterioRubrica> findByCriterioIdOrderByNivelAsc(Long criterioId);
}



