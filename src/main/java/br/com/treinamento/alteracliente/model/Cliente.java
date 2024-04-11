package br.com.treinamento.alteracliente.model;

import br.com.treinamento.alteracliente.dto.EnderecoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;
    private ModelMapper modelMapper = new ModelMapper();

    //    getter/setter id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    //    getter/setter nome
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    //    getter/setter email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    //    getter/setter telefone
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    //    getter/setter telefone
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    // getter/setter dataNascimento
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    // getter/setter endereco
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(EnderecoDTO enderecoDTO) {
        Endereco novoEndereco = modelMapper.map(enderecoDTO, Endereco.class);
        this.endereco = novoEndereco;
    }

}