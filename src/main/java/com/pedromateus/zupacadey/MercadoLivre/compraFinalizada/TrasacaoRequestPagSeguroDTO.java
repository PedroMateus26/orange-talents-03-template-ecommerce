package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;


public class TrasacaoRequestPagSeguroDTO implements TransacaoRequest{
    private Long id;
    private StatusTrasacao statusTrasacao;

    public TrasacaoRequestPagSeguroDTO(Long id, PagSeguroEnum statusTrasacao) {
        this.id = id;
        this.statusTrasacao = statusTrasacao.normaliza();
    }

    public Long getId() {
        return id;
    }

    public StatusTrasacao getStatusTrasacao() {
        return statusTrasacao;
    }

    @Override
    public Transacao convertToTransacao(Compra compra) {
        return new Transacao(
                this.id,
                compra,
                this.statusTrasacao
        );
    }
}
