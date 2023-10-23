package com.danielraguiar.logapi.domain.service;

import com.danielraguiar.logapi.domain.exception.Exceptions;
import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.model.Ocorrencia;
import com.danielraguiar.logapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OcorrenciaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaService entregaService;

    @Transactional
    public Ocorrencia registrar(Long id, String descricao) {
        Entrega entrega = entregaService.buscaEntrega(id);

        return entrega.adicionarOcorrencia(descricao);
    }

}
