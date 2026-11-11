package tcc.sagsa.repositories;

import tcc.sagsa.models.RecursoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecursoAmbienteRepository extends JpaRepository<RecursoAmbiente, Long> {
    List<RecursoAmbiente> findBySituacaoAprendizagemId(Long saId);
}


