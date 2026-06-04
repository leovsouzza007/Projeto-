package com.sistema.loja.pedido.event;

import java.util.List;

public class PedidoCriadoEvent {

    private Long pedidoId;
    private String status;
    private List<Long> produtosIds;
    private String criadoEm; // String simples — evita problema de serialização

    public PedidoCriadoEvent() {}

    public PedidoCriadoEvent(Long pedidoId, String status,
                              List<Long> produtosIds, String criadoEm) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.produtosIds = produtosIds;
        this.criadoEm = criadoEm;
    }

    public Long getPedidoId() { return pedidoId; }
    public String getStatus() { return status; }
    public List<Long> getProdutosIds() { return produtosIds; }
    public String getCriadoEm() { return criadoEm; }

    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public void setStatus(String status) { this.status = status; }
    public void setProdutosIds(List<Long> produtosIds) { this.produtosIds = produtosIds; }
    public void setCriadoEm(String criadoEm) { this.criadoEm = criadoEm; }
}