package Trabalho.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Entidade que armazena as regras dinâmicas de desconto por grau de umidade.
 * Será populada pelo administrador via API REST.
 */
@Entity
public class UmidadeDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Umidade exata que gera o desconto (ex: 14.50).
    private BigDecimal umidade;

    // Percentual de desconto aplicável para aquela umidade (ex: 0.90).
    private BigDecimal descontoPercentual;

    // Construtor padrão exigido pelo JPA
    public UmidadeDesconto() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public BigDecimal getUmidade() {
        // Garante que a umidade seja retornada com 2 casas decimais para cálculos
        return umidade != null ? umidade.setScale(2, RoundingMode.HALF_UP) : null;
    }

    public BigDecimal getDescontoPercentual() {
        // Garante que o desconto seja retornado com 2 casas decimais para cálculos
        return descontoPercentual != null ? descontoPercentual.setScale(2, RoundingMode.HALF_UP) : null;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUmidade(BigDecimal umidade) {
        this.umidade = umidade;
    }

    public void setDescontoPercentual(BigDecimal descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
    }
}