package com.sistema.loja.entrega.service;

import com.sistema.loja.entrega.model.Entrega;
import com.sistema.loja.entrega.repository.EntregaRepository;
import com.sistema.loja.entrega.dto.EntregaRequestDTO;
import com.sistema.loja.entrega.dto.EntregaResponseDTO;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public EntregaResponseDTO criarEntrega(EntregaRequestDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setPedidoId(dto.getPedidoId());
        entrega.setEndereco(dto.getEndereco());
        entrega.setStatus("PENDENTE");

        Entrega salva = repository.save(entrega);

        return new EntregaResponseDTO(
                salva.getId(),
                salva.getStatus(),
                salva.getDataEnvio(),
                salva.getDataEntrega()
        );
    }

    public EntregaResponseDTO enviar(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));

        entrega.setStatus("EM_ROTA");
        entrega.setDataEnvio(LocalDateTime.now());

        Entrega atualizada = repository.save(entrega);

        return new EntregaResponseDTO(
                atualizada.getId(),
                atualizada.getStatus(),
                atualizada.getDataEnvio(),
                atualizada.getDataEntrega()
        );
    }

    public EntregaResponseDTO finalizar(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));

        entrega.setStatus("ENTREGUE");
        entrega.setDataEntrega(LocalDateTime.now());

        Entrega atualizada = repository.save(entrega);

        return new EntregaResponseDTO(
                atualizada.getId(),
                atualizada.getStatus(),
                atualizada.getDataEnvio(),
                atualizada.getDataEntrega()
        );
    }
}