package com.danielraguiar.logapi.mapper;

import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.dto.EntregaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaDTO toDto(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toListDto(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
