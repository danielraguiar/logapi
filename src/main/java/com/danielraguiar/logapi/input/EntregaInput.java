package com.danielraguiar.logapi.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatarioInput;
    @NotNull
    private BigDecimal taxa;
}
