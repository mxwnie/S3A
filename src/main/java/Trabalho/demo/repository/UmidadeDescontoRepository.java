package Trabalho.demo.repository;

import Trabalho.demo.model.UmidadeDesconto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface UmidadeDescontoRepository extends JpaRepository<UmidadeDesconto, Long> {

    /**
     * Busca um item da tabela pelo valor exato da umidade.
     * Necessário para a regra de negócio que consulta o desconto aplicável.
     * @param umidade O valor exato da umidade (Ex: 15.50)
     * @return O item de desconto correspondente, se existir.
     */
    Optional<UmidadeDesconto> findByUmidade(BigDecimal umidade);
}
