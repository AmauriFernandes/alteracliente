package br.com.treinamento.alteracliente.service;

import br.com.treinamento.alteracliente.dto.EnderecoDTO;
import br.com.treinamento.alteracliente.model.Endereco;
import br.com.treinamento.alteracliente.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private RestTemplate restTemplate = new RestTemplate();


    // Cria novo Endereco
    public EnderecoDTO createEndereco(EnderecoDTO enderecoDTO) {
        // Converte o DTO para a entidade usando ModelMapper
        Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);

        // Salva o endereco no banco de dados
        Endereco savedEndereco = enderecoRepository.save(endereco);

        // Converte a entidade salva para DTO usando ModelMapper e retorna
        EnderecoDTO createdEnderecoDTO = modelMapper.map(savedEndereco, EnderecoDTO.class);

        return createdEnderecoDTO;
    }

    // Atualiza Endereco
    public EnderecoDTO updateEndereco(int id, EnderecoDTO enderecoDTO) {
        // Busca o endereco pelo ID vinculado
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereco não encontrado"));

        // Atualiza os campos do endereco com os valores do DTO
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumerologradouro(enderecoDTO.getNumerologradouro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setCep(enderecoDTO.getCep());

        // Salva o endereco atualizado no banco de dados
        Endereco updatedEndereco = enderecoRepository.save(endereco);

        // Converte a entidade atualizada para DTO usando ModelMapper e retorna
        EnderecoDTO updatedEnderecoDTO = modelMapper.map(updatedEndereco, EnderecoDTO.class);

        return updatedEnderecoDTO;
    }

    // Valida CEP
    public boolean isCepValido(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return !response.getBody().contains("\"erro\": true");
    }

    // Valida UF
    public boolean isUfValido(String uf) {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + uf + "/distritos";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return !response.getBody().equals("[]");
    }

    // Valida Cidade
    public boolean isCidadeValida(String cidade) {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/distritos";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody().contains("\"nome\": \"" + cidade + "\"");
    }

}
