package br.com.treinamento.alteracliente.service;

import br.com.treinamento.alteracliente.dto.ClienteDTO;
import br.com.treinamento.alteracliente.dto.EnderecoDTO;
import br.com.treinamento.alteracliente.model.Cliente;
import br.com.treinamento.alteracliente.model.Endereco;
import br.com.treinamento.alteracliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private EnderecoService enderecoService;
    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private void validateClienteDTO(ClienteDTO clienteDTO) {
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
    }

    // Cria novo cliente
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        // Valida o DTO do cliente
        validateClienteDTO(clienteDTO);

        // Converte o DTO para a entidade usando ModelMapper
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        // Cria um novo endereço usando o EnderecoDTO
        EnderecoDTO createdEnderecoDTO = enderecoService.createEndereco(clienteDTO.getEnderecoDTO());

        // Define o endereço criado no cliente
        cliente.setEndereco(createdEnderecoDTO);

        // Salva o cliente no banco de dados
        Cliente savedCliente = clienteRepository.save(cliente);

        // Converte a entidade salva para DTO usando ModelMapper e retorna
        ClienteDTO createdClienteDTO = modelMapper.map(savedCliente, ClienteDTO.class);


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

        // Converte a lista de entidades para uma lista de DTOs usando ModelMapper e retorna
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
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
        // Valida o DTO do cliente
        validateClienteDTO(clienteDTO);

        // Busca o cliente no banco de dados pelo id
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        // Atualiza os campos do cliente com os valores do DTO
         cliente.setNome(clienteDTO.getNome());
         cliente.setCpf(clienteDTO.getCpf());
         cliente.setDataNascimento(clienteDTO.getDataNascimento());

        // Verifica se existem novos dados para Endereco
        if (clienteDTO.getEnderecoDTO() != null) {
            // Obter o EnderecoDTO atual
            EnderecoDTO enderecoAtual = modelMapper.map(cliente.getEndereco(), EnderecoDTO.class);
            // Obtenha o novo Endereco
            EnderecoDTO novoEndereco = clienteDTO.getEnderecoDTO();
            // Verifica se o endereco novo é diferente do atual
            if (!novoEndereco.equals(enderecoAtual)) {
                // Atualiza o endereco
                enderecoService.updateEndereco(novoEndereco.getId(), novoEndereco);
            }
            // Atualiza o endereco
            EnderecoDTO enderecoAtualizado = enderecoService.updateEndereco(novoEndereco.getId(), novoEndereco);
            // Define o endereço atualizado no cliente
            cliente.setEndereco(enderecoAtualizado);
        }

        // Salva o cliente atualizado no banco de dados
        Cliente updatedCliente = clienteRepository.save(cliente);

        // Converte a entidade atualizada para DTO usando ModelMapper e retorna
        ClienteDTO updatedClienteDTO = modelMapper.map(updatedCliente, ClienteDTO.class);

        return updatedClienteDTO;
    }

    // Deleta cliente
    public void deleteCliente(int id) {
        // Deleta o cliente no banco de dados pelo id
        clienteRepository.deleteById(id);
    }
}