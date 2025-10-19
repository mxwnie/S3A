package Trabalho.demo.repository;

import Trabalho.demo.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository< Funcionario, Long> {


}
