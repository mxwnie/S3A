package Trabalho.demo.controller;

import Trabalho.demo.model.UmidadeDesconto;
import Trabalho.demo.service.UmidadeDescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tabela-descontos") // Rota de gerenciamento da tabela dinâmica
@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class UmidadeDescontoController {

    @Autowired
    private UmidadeDescontoService service;

    // --- Endpoints para Gerenciamento da Tabela (CRUD) ---

    // CREATE (POST /tabela-descontos) - Adiciona uma nova regra (item da tabela)
    @PostMapping
    public UmidadeDesconto adicionarItemTabela(@RequestBody UmidadeDesconto item){
        return service.salvar(item);
    }

    // READ ALL (GET /tabela-descontos) - Lista todas as regras cadastradas
    @GetMapping
    public List<UmidadeDesconto> listarTabela(){
        return service.listarTodos();
    }

    // DELETE (DELETE /tabela-descontos/{id}) - Remove uma regra pelo seu ID
    @DeleteMapping("/{id}")
    public void deletarItemTabela(@PathVariable Long id){
        service.deletar(id);
    }

    // --- Endpoint para TESTE DA REGRA DE NEGÓCIO ---

    /**
     * Endpoint para simular a chamada da regra de negócio para obter o desconto.
     * Ex: GET http://localhost:6969/tabela-descontos/calcular/14.50
     * @param umidade O percentual de umidade fornecido.
     * @return O percentual de desconto aplicável.
     */
    @GetMapping("/calcular/{umidade}")
    public BigDecimal obterDescontoPorUmidade(@PathVariable double umidade) {
        return service.calcularDesconto(umidade);
    }
}
