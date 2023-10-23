package com.danielraguiar.logapi.domain.model;

import com.danielraguiar.logapi.domain.ValidationGroups;
import com.danielraguiar.logapi.domain.exception.Exceptions;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @Valid
    @Embedded
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusEntrega statusEntrega;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setEntrega(this);
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(naoPodeSerFinalizada()) {
            throw new Exceptions("Entrega não pode ser finalizada!");
        }

        setStatusEntrega(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    private boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

    private boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatusEntrega());
    }
}
