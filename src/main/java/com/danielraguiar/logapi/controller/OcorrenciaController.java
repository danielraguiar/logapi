package com.danielraguiar.logapi.controller;

import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.model.Ocorrencia;
import com.danielraguiar.logapi.domain.service.EntregaService;
import com.danielraguiar.logapi.domain.service.OcorrenciaService;
import com.danielraguiar.logapi.dto.OcorrenciaDTO;
import com.danielraguiar.logapi.input.OcorrenciaInput;
import com.danielraguiar.logapi.mapper.OcorrenciaMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("entregas/{id}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @Autowired
    private EntregaService entregaService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrencia = ocorrenciaService.registrar(id, ocorrenciaInput.getDescricao());
        return ocorrenciaMapper.toDto(ocorrencia);
    }

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long id) {
        Entrega entrega = entregaService.buscaEntrega(id);

        return ocorrenciaMapper.toListDto(entrega.getOcorrencias());
    }
}
