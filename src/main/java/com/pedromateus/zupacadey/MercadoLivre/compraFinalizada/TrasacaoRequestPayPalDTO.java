package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TrasacaoRequestPayPalDTO implements TransacaoRequest{

    @NotNull
    private String idTransacao;
    @Min(0)
    @Max(1)
    private Integer statusTrasacao;

    public TrasacaoRequestPayPalDTO(@NotNull String idTransacao, @Min(0) @Max(1) Integer statusTrasacao) {
        this.idTransacao = idTransacao;
        this.statusTrasacao = statusTrasacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public Integer getStatusTrasacao() {
        return statusTrasacao;
    }

    @Override
    public Transacao convertToTransacao(Compra compra) {
        return new Transacao(
                this.idTransacao,
                compra,
                PayPalEnum.normaliza(this.statusTrasacao)
        );
    }

    public  interface GatwaysDePagamento {
        String gatwaysPagamento(Compra compra);
    }
}
