package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequetsDTO {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NotaFiscalRequetsDTO(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }


    @Override
    public String toString() {
        return "NotaFiscalRequetsDTO{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
