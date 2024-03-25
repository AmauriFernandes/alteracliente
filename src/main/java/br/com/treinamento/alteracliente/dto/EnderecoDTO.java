package br.com.treinamento.alteracliente.dto;

public class EnderecoDTO {
    private int id;
    private String logradouro;
    private String numerologradouro;
    private String cidade;
    private String bairro;
    private String complemento;
    private String uf;
    private int cep;

    //    getter/setter id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    //    getter/setter logradouro
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    //    getter/setter numerologradouro
    public String getNumerologradouro() { return numerologradouro; }
    public void setNumerologradouro(String numerologradouro) { this.numerologradouro = numerologradouro; }

    //    getter/setter cidade
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    //    getter/setter bairro
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    //    getter/setter complemento
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    //    getter/setter uf
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    //    getter/setter cep
    public int getCep() { return cep; }
    public void setCep(int cep) { this.cep = cep; }
}