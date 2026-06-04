package com.sistema.loja.pedido.dto;

// Equivalente ao PedidoDetalheDto da apostila
// DTO otimizado para leitura — independente da entidade de domínio
public class PedidoReadDTO {

    private Long pedidoId;
    private String status;
    private String produtosIds;   // JSON: "[1,2,3]"
    private String criadoEm;      // formatado: "26/05/2026 20:45"
    private String projetadoEm;   // quando o read model foi atualizado

    public PedidoReadDTO() {}

    public PedidoReadDTO(Long pedidoId, String status,
                          String produtosIds, String criadoEm,
                          String projetadoEm) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.produtosIds = produtosIds;
        this.criadoEm = criadoEm;
        this.projetadoEm = projetadoEm;
    }

    public Long getPedidoId() { return pedidoId; }
    public String getStatus() { return status; }
    public String getProdutosIds() { return produtosIds; }
    public String getCriadoEm() { return criadoEm; }
    public String getProjetadoEm() { return projetadoEm; }

    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public void setStatus(String status) { this.status = status; }
    public void setProdutosIds(String produtosIds) { this.produtosIds = produtosIds; }
    public void setCriadoEm(String criadoEm) { this.criadoEm = criadoEm; }
    public void setProjetadoEm(String projetadoEm) { this.projetadoEm = projetadoEm; }
}