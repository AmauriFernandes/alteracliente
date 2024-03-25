package br.com.treinamento.alteracliente.service;

import br.com.treinamento.alteracliente.dto.ClienteDTO;
import br.com.treinamento.alteracliente.model.Cliente;
import br.com.treinamento.alteracliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private ModelMapper modelMapper = new ModelMapper();

    // Cria novo cliente
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        // Valida a entrada
        if (clienteDTO == null) {
            throw new IllegalArgumentException("ClienteDTO não pode ser nulo");
        }
        if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
        }

        if (clienteDTO.getCpf() == null || clienteDTO.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF do cliente não pode ser nulo ou vazio");
        }

        // Valida se o CPF contém apenas números, pontos e hífens
        String cpf = clienteDTO.getCpf();
        if (!cpf.matches("^[0-9.-]+$")) {
            throw new IllegalArgumentException("CPF inválido. O CPF deve conter apenas números, pontos e hífens.");
        }

        if (clienteDTO.getDataNascimento() == null || clienteDTO.getDataNascimento().isEmpty()) {
            throw new IllegalArgumentException("Data de nascimento do cliente não pode ser nula ou vazia");
        }

        // Converte o DTO para a entidade
        Cliente cliente = new Cliente();
        // ID gerado automaticamente pelo banco de dados
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        // Salva o cliente no banco de dados
        Cliente savedCliente;
        try {
            savedCliente = clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o cliente", e);
        }

        // Converte a entidade salva para DTO e retorna
        ClienteDTO createdClienteDTO = new ClienteDTO();
        createdClienteDTO.setId(savedCliente.getId());
        createdClienteDTO.setNome(savedCliente.getNome());
        createdClienteDTO.setCpf(savedCliente.getCpf());
        createdClienteDTO.setDataNascimento(savedCliente.getDataNascimento());

        return createdClienteDTO;
    }

    // Lista todos os clientes
    public List<ClienteDTO> getAllClientes() {
        // Busca todos os clientes no banco de dados
        List<Cliente> clientes;

        try {
            clientes = clienteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar os clientes.", e);
        }

        // Converte a lista de entidades para uma lista de DTOs e retorna
        return clientes.stream()
                .map(cliente -> {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setId(cliente.getId());
                    clienteDTO.setNome(cliente.getNome());
                    clienteDTO.setCpf(cliente.getCpf());
                    clienteDTO.setDataNascimento(cliente.getDataNascimento());
                    return clienteDTO;
                })
                .collect(Collectors.toList());
    }

    // Busca cliente por ID
    public ClienteDTO getClienteById(int id) {
        // Busca o cliente no banco de dados pelo id
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Converte a entidade para DTO e retorna
        // Teste com Model Mapper
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    // Atualiza cliente
    public ClienteDTO updateCliente(int id, ClienteDTO clienteDTO) {
        // Busca o cliente no banco de dados pelo id
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        // Atualiza os campos do cliente com os valores do DTO
         cliente.setNome(clienteDTO.getNome());
         cliente.setCpf(clienteDTO.getCpf());
         cliente.setDataNascimento(clienteDTO.getDataNascimento());

        // Salva o cliente atualizado no banco de dados
        Cliente updatedCliente = clienteRepository.save(cliente);

        // Converte a entidade atualizada para DTO e retorna
        ClienteDTO updatedClienteDTO = new ClienteDTO();
        // Novos valores para o cliente
         updatedClienteDTO.setNome(updatedCliente.getNome());
         updatedClienteDTO.setCpf(updatedCliente.getCpf());
         updatedClienteDTO.setDataNascimento(updatedCliente.getDataNascimento());

        return updatedClienteDTO;
    }

    // Deleta cliente
    public void deleteCliente(int id) {
        // Deleta o cliente no banco de dados pelo id
        clienteRepository.deleteById(id);
    }
}