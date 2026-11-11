package tcc.sagsa.repositories;

import tcc.sagsa.models.Subfuncao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubfuncaoRepository extends JpaRepository<Subfuncao, Long> {
    List<Subfuncao> findByFuncaoId(Long funcaoId);
}