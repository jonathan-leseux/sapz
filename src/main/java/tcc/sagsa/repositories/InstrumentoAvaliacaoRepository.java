package tcc.sagsa.repositories;

import tcc.sagsa.models.InstrumentoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InstrumentoAvaliacaoRepository extends JpaRepository<InstrumentoAvaliacao, Long> {
    List<InstrumentoAvaliacao> findBySituacaoAprendizagemId(Long saId);
}


