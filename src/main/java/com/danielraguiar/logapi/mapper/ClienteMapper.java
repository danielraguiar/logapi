package com.danielraguiar.logapi.mapper;

import com.danielraguiar.logapi.domain.model.Cliente;
import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.dto.ClienteDTO;
import com.danielraguiar.logapi.dto.EntregaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {

    private ModelMapper modelMapper;

    public ClienteDTO toDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> toListDto(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
