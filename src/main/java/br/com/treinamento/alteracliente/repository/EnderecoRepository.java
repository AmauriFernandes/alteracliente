package br.com.treinamento.alteracliente.repository;

import br.com.treinamento.alteracliente.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}