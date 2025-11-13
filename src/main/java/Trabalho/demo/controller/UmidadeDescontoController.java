package Trabalho.demo.controller;

import Trabalho.demo.model.CalculoPesoResponse;
import Trabalho.demo.model.HistoricoCalculo;
import Trabalho.demo.model.UmidadeDesconto;
import Trabalho.demo.repository.HistoricoCalculoRepository;
import Trabalho.demo.service.UmidadeDescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class UmidadeDescontoController {

    @Autowired
    private UmidadeDescontoService service;

    @Autowired
    private HistoricoCalculoRepository historicoRepository; // Repositório para salvar o histórico

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
        BigDecimal descontoEmKg = pesoLiquido.multiply(percentualDesconto.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));

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

        // 6. Salvar o resultado no histórico
        salvarHistorico(response);

        return response;
    }

    private void salvarHistorico(CalculoPesoResponse response) {
        HistoricoCalculo historico = new HistoricoCalculo();
        historico.setPesoBruto(response.getPesoBruto());
        historico.setTara(response.getTara());
        historico.setPesoLiquido(response.getPesoLiquido());
        historico.setUmidadeInformada(response.getUmidadeInformada());
        historico.setPercentualDescontoAplicado(response.getPercentualDescontoAplicado());
        historico.setDescontoEmKg(response.getDescontoEmKg());
        historico.setPesoFinal(response.getPesoFinal());
        historico.setDataCalculo(LocalDateTime.now()); // Adiciona a data e hora do cálculo
        historicoRepository.save(historico);
    }

    // --- Endpoint para consultar o histórico ---

    @GetMapping("/romaneio/historico")
    public List<HistoricoCalculo> listarHistorico() {
        return historicoRepository.findAll();
    }


    // --- Endpoints Auxiliares ---

    @GetMapping("/descontos/calcular-por-umidade/{umidade}")
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
