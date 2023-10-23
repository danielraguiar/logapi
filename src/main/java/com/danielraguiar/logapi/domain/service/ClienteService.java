package com.danielraguiar.logapi.domain.service;

import com.danielraguiar.logapi.domain.exception.Exceptions;
import com.danielraguiar.logapi.domain.model.Cliente;
import com.danielraguiar.logapi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailEmUso) {
            throw new Exceptions("Já existe um cliente cadastrado com esse email.");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new Exceptions("Cliente não encontrado"));
    }
}
