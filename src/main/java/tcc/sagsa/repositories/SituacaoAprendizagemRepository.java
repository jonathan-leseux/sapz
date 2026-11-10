package tcc.sagsa.repositories;

import tcc.sagsa.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SituacaoAprendizagemRepository extends JpaRepository<SituacaoAprendizagem, Long> {
    List<SituacaoAprendizagem> findByInstrutorId(Long instrutorId);
    List<SituacaoAprendizagem> findByUnidadeCurricularId(Long unidadeCurricularId);

    @Query("SELECT sa FROM SituacaoAprendizagem sa WHERE sa.compartilhada = true OR sa.instrutor.id = :instrutorId")
    List<SituacaoAprendizagem> findVisiveisParaInstrutor(@Param("instrutorId") Long instrutorId);
}