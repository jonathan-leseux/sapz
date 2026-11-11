package tcc.sagsa.repositories;

import tcc.sagsa.models.EstrategiaEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstrategiaEnsinoRepository extends JpaRepository<EstrategiaEnsino, Long> {
    List<EstrategiaEnsino> findBySituacaoAprendizagemId(Long saId);
}


