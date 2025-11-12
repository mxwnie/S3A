package Trabalho.demo.controller;

import Trabalho.demo.model.CalculoPesoResponse;
import Trabalho.demo.model.UmidadeDesconto;
import Trabalho.demo.service.UmidadeDescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/api") // Rota base para a API
@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class UmidadeDescontoController {

    @Autowired
    private UmidadeDescontoService service;

    // --- Endpoint Principal para o Cálculo do Romaneio ---

    @GetMapping("/romaneio/calcular")
    public CalculoPesoResponse calcularRomaneio(
            @RequestParam double pesoBruto,
            @RequestParam double tara,
            @RequestParam double umidade) {

        BigDecimal pesoBrutoBD = BigDecimal.valueOf(pesoBruto);
        BigDecimal taraBD = BigDecimal.valueOf(tara);

        // 1. Calcular Peso Líquido
        BigDecimal pesoLiquido = pesoBrutoBD.subtract(taraBD);

        // 2. Obter o percentual de desconto de umidade
        BigDecimal percentualDesconto = service.calcularDesconto(umidade);

        // 3. Calcular o peso do desconto em KG
        BigDecimal descontoEmKg = pesoLiquido.multiply(percentualDesconto.divide(BigDecimal.valueOf(100)));

        // 4. Calcular o peso final a pagar
        BigDecimal pesoFinal = pesoLiquido.subtract(descontoEmKg);

        // 5. Montar a resposta completa
        CalculoPesoResponse response = new CalculoPesoResponse();
        response.setPesoBruto(pesoBrutoBD.setScale(2, RoundingMode.HALF_UP));
        response.setTara(taraBD.setScale(2, RoundingMode.HALF_UP));
        response.setPesoLiquido(pesoLiquido.setScale(2, RoundingMode.HALF_UP));
        response.setUmidadeInformada(umidade);
        response.setPercentualDescontoAplicado(percentualDesconto.setScale(2, RoundingMode.HALF_UP));
        response.setDescontoEmKg(descontoEmKg.setScale(2, RoundingMode.HALF_UP));
        response.setPesoFinal(pesoFinal.setScale(2, RoundingMode.HALF_UP));

        return response;
    }

    // --- Endpoints Auxiliares ---

    /**
     * Endpoint para testar a regra de cálculo de desconto de umidade isoladamente.
     * Ex: GET http://localhost:6969/api/descontos/calcular-por-umidade/14.50
     */
    @GetMapping("/descontos/calcular-por-umidade/{umidade:.+}")
    public BigDecimal obterDescontoPorUmidade(@PathVariable double umidade) {
        return service.calcularDesconto(umidade);
    }

    // --- Endpoints para Gerenciamento da Tabela (se necessário no futuro) ---

    @PostMapping("/descontos/regras")
    public UmidadeDesconto adicionarItemTabela(@RequestBody UmidadeDesconto item){
        return service.salvar(item);
    }

    @GetMapping("/descontos/regras")
    public List<UmidadeDesconto> listarTabela(){
        return service.listarTodos();
    }

    @DeleteMapping("/descontos/regras/{id}")
    public void deletarItemTabela(@PathVariable Long id){
        service.deletar(id);
    }
}
