package tcc.sagsa.repositories;

import tcc.sagsa.models.PadraoDesempenho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PadraoDesempenhoRepository extends JpaRepository<PadraoDesempenho, Long> {
    List<PadraoDesempenho> findBySubfuncaoId(Long subfuncaoId);
}


