package com.sistema.loja.entrega.service;

import com.sistema.loja.entrega.dto.EntregaRequestDTO;
import com.sistema.loja.entrega.dto.EntregaResponseDTO;
import com.sistema.loja.entrega.enums.StatusEntrega;
import com.sistema.loja.entrega.model.Entrega;
import com.sistema.loja.entrega.repository.EntregaRepository;
import com.sistema.loja.exception.RecursoNaoEncontradoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository repository;

    @InjectMocks
    private EntregaService service;

    @Test
    void deveCriarEntrega() {

        EntregaRequestDTO dto = new EntregaRequestDTO();
        dto.setPedidoId(1L);
        dto.setEndereco("Rua A, 123");

        Entrega entrega = new Entrega();
        entrega.setPedidoId(1L);
        entrega.setEndereco("Rua A, 123");
        entrega.setStatus(StatusEntrega.PENDENTE);

        when(repository.save(any()))
                .thenReturn(entrega);

        EntregaResponseDTO response =
                service.criarEntrega(dto);

        assertNotNull(response);

        assertEquals(
                StatusEntrega.PENDENTE,
                response.getStatus()
        );

        verify(repository, times(1))
                .save(any());
    }

    @Test
    void deveEnviarEntrega() {

        Entrega entrega = new Entrega();
        entrega.setStatus(StatusEntrega.PENDENTE);

        when(repository.findById(1L))
                .thenReturn(Optional.of(entrega));

        when(repository.save(any()))
                .thenReturn(entrega);

        EntregaResponseDTO response =
                service.enviar(1L);

        assertEquals(
                StatusEntrega.EM_ROTA,
                response.getStatus()
        );

        verify(repository, times(1))
                .save(any());
    }

    @Test
    void deveFinalizarEntrega() {

        Entrega entrega = new Entrega();
        entrega.setStatus(StatusEntrega.EM_ROTA);
        entrega.setDataEnvio(LocalDateTime.now());

        when(repository.findById(1L))
                .thenReturn(Optional.of(entrega));

        when(repository.save(any()))
                .thenReturn(entrega);

        EntregaResponseDTO response =
                service.finalizar(1L);

        assertEquals(
                StatusEntrega.ENTREGUE,
                response.getStatus()
        );

        verify(repository, times(1))
                .save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEntregaNaoExistir() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.enviar(99L)
        );
    }
}