package com.danielraguiar.logapi.domain.service;

import com.danielraguiar.logapi.domain.exception.Exceptions;
import com.danielraguiar.logapi.domain.model.Cliente;
import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.model.StatusEntrega;
import com.danielraguiar.logapi.domain.repository.ClienteRepository;
import com.danielraguiar.logapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteService clienteService;
    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }


}
