package Trabalho.demo.service;

import Trabalho.demo.model.Caminhao;
import Trabalho.demo.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço que contém a lógica de negócio para a entidade Caminhao.
 */
@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    @Autowired
    public CaminhaoService(CaminhaoRepository caminhaoRepository) {
        this.caminhaoRepository = caminhaoRepository;
    }

    /**
     * Retorna todos os caminhões cadastrados.
     * @return Lista de Caminhao.
     */
    public List<Caminhao> findAll() {
        return caminhaoRepository.findAll();
    }

    /**
     * Busca um caminhão pelo seu ID.
     * @param id O ID do caminhão.
     * @return Um Optional contendo o Caminhao, se encontrado.
     */
    public Optional<Caminhao> findById(Long id) {
        return caminhaoRepository.findById(id);
    }

    /**
     * Salva um novo caminhão ou atualiza um existente (se o ID estiver presente).
     * @param caminhao O objeto Caminhao a ser salvo.
     * @return O objeto Caminhao salvo.
     */
    public Caminhao save(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    /**
     * Atualiza um caminhão existente.
     * @param id O ID do caminhão a ser atualizado.
     * @param caminhaoDetails Os novos detalhes do caminhão.
     * @return O Caminhao atualizado.
     * @throws RuntimeException Se o caminhão não for encontrado.
     */
    public Caminhao update(Long id, Caminhao caminhaoDetails) {
        return caminhaoRepository.findById(id)
                .map(caminhaoExistente -> {
                    // Atualiza todos os campos relevantes
                    caminhaoExistente.setMarca(caminhaoDetails.getMarca());
                    caminhaoExistente.setModelo(caminhaoDetails.getModelo());
                    caminhaoExistente.setPlaca(caminhaoDetails.getPlaca());
                    caminhaoExistente.setAnoFabricacao(caminhaoDetails.getAnoFabricacao());
                    caminhaoExistente.setCapacidadeCargaKg(caminhaoDetails.getCapacidadeCargaKg());
                    caminhaoExistente.setDataUltimaManutencao(caminhaoDetails.getDataUltimaManutencao());
                    return caminhaoRepository.save(caminhaoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado com ID: " + id));
    }

    /**
     * Deleta um caminhão pelo seu ID.
     * @param id O ID do caminhão a ser deletado.
     * @throws RuntimeException Se o caminhão não for encontrado.
     */
    public void delete(Long id) {
        if (!caminhaoRepository.existsById(id)) {
            throw new RuntimeException("Caminhão não encontrado com ID: " + id);
        }
        caminhaoRepository.deleteById(id);
    }
}
