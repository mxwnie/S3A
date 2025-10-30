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
     * Calcula o percentual de desconto com base na umidade, consultando a tabela dinâmica no banco de dados.
     * @param umidadePercentual Umidade do grão (Ex: 15.5).
     * @return O percentual de desconto (Ex: 4.00) ou 0.00 se não houver regra ou se for <= 14.00.
     */
    public BigDecimal calcularDesconto(double umidadePercentual) {
        // 1. Prepara a umidade para busca, garantindo precisão com 2 casas decimais.
        BigDecimal umidadeChave = BigDecimal.valueOf(umidadePercentual)
                .setScale(2, RoundingMode.HALF_UP);

        // 2. Regra: Umidade de 14.00% ou menos não tem desconto.
        if (umidadeChave.compareTo(BigDecimal.valueOf(14.00)) <= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        // 3. Busca o valor exato no banco de dados
        Optional<UmidadeDesconto> resultado = repository.findByUmidade(umidadeChave);

        if (resultado.isPresent()) {
            return resultado.get().getDescontoPercentual();
        } else {
            // Se o valor exato não for encontrado (ex: 14.51%), retorna 0 e loga o aviso.
            System.err.println("Aviso: Umidade " + umidadePercentual + "% não possui desconto cadastrado (valor exato não encontrado).");
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
```eof