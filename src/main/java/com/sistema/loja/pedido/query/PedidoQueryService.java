package com.sistema.loja.pedido.query;

import com.sistema.loja.pedido.dto.PedidoReadDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

// Equivalente ao ObterPedidoPorIdHandler com Dapper da apostila
// Read Side: consultas SQL diretas sem JPA, sem tracking, sem overhead
@Service
public class PedidoQueryService {

    private static final Logger log =
            LoggerFactory.getLogger(PedidoQueryService.class);

    private final JdbcTemplate jdbc;

    public PedidoQueryService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Lista todos os pedidos do Read Model — equivalente à ListarPedidosClienteQuery
    public List<PedidoReadDTO> listarTodos() {
        String sql = """
                SELECT
                    pedido_id,
                    status,
                    produtos_ids,
                    criado_em,
                    CAST(projetado_em AS VARCHAR) AS projetado_em
                FROM pedidos_read_model
                ORDER BY projetado_em DESC
                """;

        return jdbc.query(sql, (rs, rowNum) -> new PedidoReadDTO(
                rs.getLong("pedido_id"),
                rs.getString("status"),
                rs.getString("produtos_ids"),
                rs.getString("criado_em"),
                rs.getString("projetado_em")
        ));
    }

    // Busca pedido por ID no Read Model
    public PedidoReadDTO buscarPorId(Long pedidoId) {
        String sql = """
                SELECT
                    pedido_id,
                    status,
                    produtos_ids,
                    criado_em,
                    CAST(projetado_em AS VARCHAR) AS projetado_em
                FROM pedidos_read_model
                WHERE pedido_id = ?
                """;

        List<PedidoReadDTO> resultado = jdbc.query(sql,
                (rs, rowNum) -> new PedidoReadDTO(
                        rs.getLong("pedido_id"),
                        rs.getString("status"),
                        rs.getString("produtos_ids"),
                        rs.getString("criado_em"),
                        rs.getString("projetado_em")
                ),
                pedidoId
        );

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    // Filtra por status — demonstra flexibilidade do SQL direto
    public List<PedidoReadDTO> listarPorStatus(String status) {
        String sql = """
                SELECT
                    pedido_id,
                    status,
                    produtos_ids,
                    criado_em,
                    CAST(projetado_em AS VARCHAR) AS projetado_em
                FROM pedidos_read_model
                WHERE status = ?
                ORDER BY projetado_em DESC
                """;

        return jdbc.query(sql, (rs, rowNum) -> new PedidoReadDTO(
                rs.getLong("pedido_id"),
                rs.getString("status"),
                rs.getString("produtos_ids"),
                rs.getString("criado_em"),
                rs.getString("projetado_em")
        ), status);
    }
}