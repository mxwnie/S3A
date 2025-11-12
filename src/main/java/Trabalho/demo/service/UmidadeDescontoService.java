package Trabalho.demo.service;

import Trabalho.demo.model.UmidadeDesconto;
import Trabalho.demo.repository.UmidadeDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class UmidadeDescontoService {

    @Autowired
    private UmidadeDescontoRepository repository;

    // --- MÉTODOS CRUD BÁSICOS (Gerenciamento da Tabela) ---

    public UmidadeDesconto salvar(UmidadeDesconto item) {
        return repository.save(item);
    }

    public List<UmidadeDesconto> listarTodos() {
        return repository.findAll();
    }

    public Optional<UmidadeDesconto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // --- REGRA DE NEGÓCIO PRINCIPAL ---

    /**
     * Calcula o percentual de desconto com base na umidade, usando uma fórmula por faixas.
     * @param umidadePercentual Umidade do grão (Ex: 15.5).
     * @return O percentual de desconto calculado.
     */
    public BigDecimal calcularDesconto(double umidadePercentual) {
        double m = umidadePercentual;
        double desconto;

        if (m <= 14.0) {
            desconto = 0.0;
        } else if (m <= 14.5) {
            desconto = (m - 14.0) * 3.0;
        } else if (m <= 25.0) {
            desconto = 1.5 + (m - 14.5) * 1.5;
        } else if (m <= 26.0) {
            desconto = 17.25 + (m - 25.0) * 2.0;
        } else if (m < 27.0) {
            desconto = 19.25 + (m - 26.0) * 2.0;
        } else if (m <= 35.2) {
            desconto = 22.00 + (m - 27.0) * 3.0;
        } else {
            desconto = 46.60 + (m - 35.2) * 5.0;
        }

        return BigDecimal.valueOf(desconto).setScale(2, RoundingMode.HALF_UP);
    }
}
