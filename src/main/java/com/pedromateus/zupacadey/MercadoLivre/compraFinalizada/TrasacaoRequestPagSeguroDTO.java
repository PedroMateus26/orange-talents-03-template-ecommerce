package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;


public class TrasacaoRequestPagSeguroDTO implements TransacaoRequest{
    private String idTransacao;
    private PagSeguroEnum statusTrasacao;

    public TrasacaoRequestPagSeguroDTO(String idTransacao, PagSeguroEnum statusTrasacao) {
        this.idTransacao = idTransacao;
        this.statusTrasacao = statusTrasacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public PagSeguroEnum getStatusTrasacao() {
        return statusTrasacao;
    }

    @Override
    public Transacao convertToTransacao(Compra compra) {
        return new Transacao(
                this.idTransacao,
                compra,
                this.statusTrasacao.normaliza()
        );
    }
}
