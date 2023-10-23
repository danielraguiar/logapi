package com.danielraguiar.logapi.mapper;

import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.model.Ocorrencia;
import com.danielraguiar.logapi.dto.EntregaDTO;
import com.danielraguiar.logapi.dto.OcorrenciaDTO;
import com.danielraguiar.logapi.input.EntregaInput;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaDTO toDto(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toListDto(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
