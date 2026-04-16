package com.sistema.loja.entrega.dto;

import java.time.LocalDateTime;

public class EntregaResponseDTO {

    private Long id;
    private String status;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataEntrega;

    public EntregaResponseDTO(Long id, String status,
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }
}