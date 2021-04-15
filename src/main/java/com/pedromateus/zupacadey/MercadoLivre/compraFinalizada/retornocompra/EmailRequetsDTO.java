package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmailRequetsDTO {
    @NotNull
    private Long idCompra;
    @NotBlank
    private String emailResposta;

    public EmailRequetsDTO(@NotNull Long idCompra, @NotNull String emailResposta) {
        this.idCompra = idCompra;
        this.emailResposta = emailResposta;
    }

    @Override
    public String toString() {
        return "EmailRequetsDTO{" +
                "idCompra=" + idCompra +
                ", emailResposta='" + emailResposta + '\'' +
                '}';
    }
}
