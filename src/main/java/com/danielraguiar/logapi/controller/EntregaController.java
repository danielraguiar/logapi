package com.danielraguiar.logapi.controller;

import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.repository.ClienteRepository;
import com.danielraguiar.logapi.domain.repository.EntregaRepository;
import com.danielraguiar.logapi.domain.service.EntregaService;
import com.danielraguiar.logapi.dto.EntregaDTO;
import com.danielraguiar.logapi.input.EntregaInput;
import com.danielraguiar.logapi.mapper.EntregaMapper;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private EntregaMapper entregaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        return entregaMapper.toDto(entregaService.solicitar(entregaMapper.toEntity(entregaInput)));
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return entregaMapper.toListDto(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toDto(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        entregaService.finalizar(id);
    }
}
