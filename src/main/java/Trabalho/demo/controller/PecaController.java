package Trabalho.demo.controller;

import Trabalho.demo.model.Peca;
import Trabalho.demo.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pecas") // URL base para todas as operações de peça
@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class PecaController {

    @Autowired
    private PecaService pecaService;

    // CREATE (POST /pecas)
    @PostMapping
    public Peca criarPeca(@RequestBody Peca peca){
        return pecaService.criarPeca(peca);
    }

    // READ - Listar todos (GET /pecas)
    @GetMapping
    public List<Peca> listarPecas(){
        return pecaService.listarPecas();
    }

    // READ - Buscar por ID (GET /pecas/{id})
    @GetMapping("/{id}")
    public Optional<Peca> buscarPeca(@PathVariable Long id){
        return pecaService.buscarPeca(id);
    }

    // UPDATE (PUT /pecas/{id})
    @PutMapping("/{id}")
    public Peca atualizarPeca(@PathVariable Long id, @RequestBody Peca pecaAtualizada){
        return pecaService.atualizarPeca(id, pecaAtualizada);
    }

    // DELETE (DELETE /pecas/{id})
    @DeleteMapping("/{id}")
    public void deletarPeca(@PathVariable Long id){
        pecaService.deletarPeca(id);
    }
}