package com.danielraguiar.logapi.dto;

import com.danielraguiar.logapi.domain.model.Entrega;
import com.danielraguiar.logapi.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaDTO {

    private Long id;
    private ClienteDTO cliente;
    private BigDecimal taxa;
    private DestinatarioDTO destinatarioDTO;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
