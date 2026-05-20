package com.sistema.loja.entrega.dto;

import com.sistema.loja.entrega.enums.StatusEntrega;

import java.time.LocalDateTime;

public class EntregaResponseDTO {

    private Long id;

    private StatusEntrega status;

    private LocalDateTime dataEnvio;

    private LocalDateTime dataEntrega;

    public EntregaResponseDTO(Long id,
                              StatusEntrega status,
                              LocalDateTime dataEnvio,
                              LocalDateTime dataEntrega) {

        this.id = id;
        this.status = status;
        this.dataEnvio = dataEnvio;
        this.dataEntrega = dataEntrega;
    }

    public Long getId() {
        return id;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
}