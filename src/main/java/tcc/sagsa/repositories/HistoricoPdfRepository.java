package tcc.sagsa.repositories;

import tcc.sagsa.models.HistoricoPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoPdfRepository extends JpaRepository<HistoricoPdf, Long> {
    List<HistoricoPdf> findBySituacaoAprendizagemId(Long saId);
}