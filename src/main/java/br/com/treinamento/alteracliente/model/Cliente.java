package br.com.treinamento.alteracliente.model;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String dataNascimento;

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
}