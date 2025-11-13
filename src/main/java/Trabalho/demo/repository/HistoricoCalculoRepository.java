package Trabalho.demo.repository;

import Trabalho.demo.model.HistoricoCalculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoCalculoRepository extends JpaRepository<HistoricoCalculo, Long> {
}
