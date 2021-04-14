package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;

public class TrasacaoRequestPayPalDTO implements TransacaoRequest{

    private Long id;
    private Integer statusTrasacao;

    public TrasacaoRequestPayPalDTO(Long id, Integer statusTrasacao) {
        this.id = id;
        this.statusTrasacao = statusTrasacao;
    }

    public Long getId() {
        return id;
    }

    public Integer getStatusTrasacao() {
        return statusTrasacao;
    }

    @Override
    public Transacao convertToTransacao(Compra compra) {
        return new Transacao(
                this.id,
                compra,
                PayPalEnum.normaliza(this.statusTrasacao)

        );
    }
}
