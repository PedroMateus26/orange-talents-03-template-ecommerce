package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra;

import javax.validation.constraints.NotNull;

public class RankingRequetsDTO {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingRequetsDTO(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequetsDTO{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
