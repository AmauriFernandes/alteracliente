package br.com.treinamento.alteracliente.repository;

import br.com.treinamento.alteracliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}