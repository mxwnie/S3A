package Trabalho.demo.service;

import Trabalho.demo.model.Peca;
import Trabalho.demo.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    // CREATE
    public Peca criarPeca(Peca peca){
        return pecaRepository.save(peca);
    }

    // READ - Listar todos
    public List<Peca> listarPecas(){
        return pecaRepository.findAll();
    }

    // READ - Buscar por ID
    public Optional<Peca> buscarPeca(Long id){
        return pecaRepository.findById(id);
    }

    // UPDATE
    public Peca atualizarPeca(Long id, Peca pecaAtualizada){
        return pecaRepository.findById(id).map(peca -> {
            // Atualiza os campos da peça existente com os dados da peçaAtualizada
            peca.setNome(pecaAtualizada.getNome());
            peca.setDescricao(pecaAtualizada.getDescricao());
            peca.setFabricante(pecaAtualizada.getFabricante());
            peca.setNumeroDeSerie(pecaAtualizada.getNumeroDeSerie());
            peca.setPrecoUnitario(pecaAtualizada.getPrecoUnitario());
            peca.setQuantidadeEmEstoque(pecaAtualizada.getQuantidadeEmEstoque());

            // Salva e retorna a peça atualizada
            return pecaRepository.save(peca);
        }).orElseThrow(() -> new RuntimeException("Peça não encontrada com id " + id)); // Trata se a peça não existir
    }

    // DELETE
    public void deletarPeca(Long id){
        pecaRepository.deleteById(id);
    }
}