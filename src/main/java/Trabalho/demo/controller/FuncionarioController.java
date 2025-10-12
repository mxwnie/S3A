package Trabalho.demo.controller;

import Trabalho.demo.model.Funcionario;
import Trabalho.demo.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // CREATE
    @PostMapping
    public Funcionario criarFuncionario(@RequestBody Funcionario funcionario){ //vem do controller atraves do request ele ta preenchido com os dados enviados pelo cliente em jSON(nome cpf, cargo ,etc)
        return funcionarioService.criarFuncionario(funcionario);
    }

    // READ - Listar todos
    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    // READ - Buscar por ID
    @GetMapping("/{id}")
    public Optional<Funcionario> buscarFuncionario(@PathVariable Long id){
        return funcionarioService.buscarFuncionario(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Funcionario atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado){
        return funcionarioService.atualizarFuncionario(id, funcionarioAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletarFuncionario(@PathVariable Long id){
        funcionarioService.deletarFuncionario(id);
    }
}
