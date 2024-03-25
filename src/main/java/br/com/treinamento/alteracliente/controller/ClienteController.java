package br.com.treinamento.alteracliente.controller;

import br.com.treinamento.alteracliente.dto.ClienteDTO;
import br.com.treinamento.alteracliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
        return ResponseEntity.ok(createdCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int id) {
        ClienteDTO cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable int id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO updatedCliente = clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}