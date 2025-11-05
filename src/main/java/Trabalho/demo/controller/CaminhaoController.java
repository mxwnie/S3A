package Trabalho.demo.controller;

import Trabalho.demo.model.Caminhao;
import Trabalho.demo.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para a entidade Caminhao.
 * Define os endpoints (rotas) da API.
 * A rota base é /api/caminhoes.
 */
@RestController
@RequestMapping("/api/caminhoes")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    @Autowired
    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    // Endpoint: GET /api/caminhoes
    // Retorna todos os caminhões
    @GetMapping
    public List<Caminhao> getAllCaminhoes() {
        return caminhaoService.findAll();
    }

    // Endpoint: GET /api/caminhoes/{id}
    // Retorna um caminhão específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> getCaminhaoById(@PathVariable Long id) {
        return caminhaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint: POST /api/caminhoes
    // Cria um novo caminhão
    @PostMapping
    public Caminhao createCaminhao(@RequestBody Caminhao caminhao) {
        return caminhaoService.save(caminhao);
    }

    // Endpoint: PUT /api/caminhoes/{id}
    // Atualiza um caminhão existente
    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> updateCaminhao(@PathVariable Long id, @RequestBody Caminhao caminhaoDetails) {
        try {
            Caminhao caminhaoAtualizado = caminhaoService.update(id, caminhaoDetails);
            return ResponseEntity.ok(caminhaoAtualizado);
        } catch (RuntimeException e) {
            // Em um ambiente real, você pode retornar 404 Not Found com uma mensagem de erro
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint: DELETE /api/caminhoes/{id}
    // Deleta um caminhão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaminhao(@PathVariable Long id) {
        try {
            caminhaoService.delete(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content para sucesso na exclusão
        } catch (RuntimeException e) {
            // Se o caminhão não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
