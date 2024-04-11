package br.com.treinamento.alteracliente.controller;

import br.com.treinamento.alteracliente.dto.EnderecoDTO;
import br.com.treinamento.alteracliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoDTO> createEndereco(@PathVariable int clienteId, @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO createdEndereco = enderecoService.createEndereco(clienteId, enderecoDTO);
        return ResponseEntity.ok(createdEndereco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable int clienteId, @PathVariable int id) {
        EnderecoDTO endereco = enderecoService.getEnderecoById(clienteId, id);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable int clienteId, @PathVariable int id, @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO updatedEndereco = enderecoService.updateEndereco(clienteId, id, enderecoDTO);
        return ResponseEntity.ok(updatedEndereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable int clienteId, @PathVariable int id) {
        enderecoService.deleteEndereco(clienteId, id);
        return ResponseEntity.noContent().build();
    }
}