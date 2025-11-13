package Trabalho.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class HistoricoCalculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal pesoBruto;
    private BigDecimal tara;
    private BigDecimal pesoLiquido;
    private Double umidadeInformada;
    private BigDecimal percentualDescontoAplicado;
    private BigDecimal descontoEmKg;
    private BigDecimal pesoFinal;
    private LocalDateTime dataCalculo;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public BigDecimal getTara() {
        return tara;
    }

    public void setTara(BigDecimal tara) {
        this.tara = tara;
    }

    public BigDecimal getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(BigDecimal pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public Double getUmidadeInformada() {
        return umidadeInformada;
    }

    public void setUmidadeInformada(Double umidadeInformada) {
        this.umidadeInformada = umidadeInformada;
    }

    public BigDecimal getPercentualDescontoAplicado() {
        return percentualDescontoAplicado;
    }

    public void setPercentualDescontoAplicado(BigDecimal percentualDescontoAplicado) {
        this.percentualDescontoAplicado = percentualDescontoAplicado;
    }

    public BigDecimal getDescontoEmKg() {
        return descontoEmKg;
    }

    public void setDescontoEmKg(BigDecimal descontoEmKg) {
        this.descontoEmKg = descontoEmKg;
    }

    public BigDecimal getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(BigDecimal pesoFinal) {
        this.pesoFinal = pesoFinal;
    }

    public LocalDateTime getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDateTime dataCalculo) {
        this.dataCalculo = dataCalculo;
    }
}
