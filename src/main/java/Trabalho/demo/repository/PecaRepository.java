package Trabalho.demo.repository;

import Trabalho.demo.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário.
    // Por enquanto, o JpaRepository já fornece o que precisamos.
}