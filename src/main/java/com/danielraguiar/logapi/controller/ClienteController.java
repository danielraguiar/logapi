package com.danielraguiar.logapi.controller;

import com.danielraguiar.logapi.domain.model.Cliente;
import com.danielraguiar.logapi.domain.repository.ClienteRepository;
import com.danielraguiar.logapi.domain.service.ClienteService;
import com.danielraguiar.logapi.dto.ClienteDTO;
import com.danielraguiar.logapi.mapper.ClienteMapper;
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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteMapper.toListDto(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(clienteMapper.toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteMapper.toDto(clienteService.salvar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        cliente = clienteService.salvar(cliente);

        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
