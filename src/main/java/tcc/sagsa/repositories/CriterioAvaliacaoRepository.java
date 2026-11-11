package tcc.sagsa.repositories;

import tcc.sagsa.models.CriterioAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CriterioAvaliacaoRepository extends JpaRepository<CriterioAvaliacao, Long> {
    List<CriterioAvaliacao> findByInstrumentoId(Long instrumentoId);
}



