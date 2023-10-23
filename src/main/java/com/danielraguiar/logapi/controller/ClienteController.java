package com.danielraguiar.logapi.controller;

import com.danielraguiar.logapi.domain.model.Cliente;
import com.danielraguiar.logapi.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
