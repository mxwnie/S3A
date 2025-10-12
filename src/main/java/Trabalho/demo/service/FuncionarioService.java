package Trabalho.demo.service;

import Trabalho.demo.model.Funcionario;
import Trabalho.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // CREATE
    public Funcionario criarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    // READ - Listar todos
    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    // READ - Buscar por ID
    public Optional<Funcionario> buscarFuncionario(Long id){
        return funcionarioRepository.findById(id);
    }

    // UPDATE
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado){
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCpf(funcionarioAtualizado.getCpf());
            funcionario.setCargo(funcionarioAtualizado.getCargo());
            funcionario.setSalario(funcionarioAtualizado.getSalario());
            funcionario.setDataAdmissao(funcionarioAtualizado.getDataAdmissao());
            funcionario.setTelefone(funcionarioAtualizado.getTelefone());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id " + id));
    }

    // DELETE
    public void deletarFuncionario(Long id){
        funcionarioRepository.deleteById(id);
    }
}
