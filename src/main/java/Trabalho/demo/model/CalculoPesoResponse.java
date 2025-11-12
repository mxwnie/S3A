package Trabalho.demo.model;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para encapsular a resposta completa do cálculo de pesagem.
 */
public class CalculoPesoResponse {

    private BigDecimal pesoBruto;
    private BigDecimal tara;
    private BigDecimal pesoLiquido;
    private double umidadeInformada;
    private BigDecimal percentualDescontoAplicado;
    private BigDecimal descontoEmKg;
    private BigDecimal pesoFinal;

    // Getters e Setters

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

    public double getUmidadeInformada() {
        return umidadeInformada;
    }

    public void setUmidadeInformada(double umidadeInformada) {
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
}
