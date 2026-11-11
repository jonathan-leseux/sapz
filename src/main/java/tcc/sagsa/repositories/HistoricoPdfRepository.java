package tcc.sagsa.repositories;

import tcc.sagsa.models.Compartilhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoPdfRepository  extends JpaRepository<Compartilhamento, Long> {
    List<Compartilhamento> findBySituacaoAprendizagemId(Long saId);
}
