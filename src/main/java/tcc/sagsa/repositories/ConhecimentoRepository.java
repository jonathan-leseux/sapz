package tcc.sagsa.repositories;

import tcc.sagsa.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConhecimentoRepository extends JpaRepository<Conhecimento, Long> {
}
