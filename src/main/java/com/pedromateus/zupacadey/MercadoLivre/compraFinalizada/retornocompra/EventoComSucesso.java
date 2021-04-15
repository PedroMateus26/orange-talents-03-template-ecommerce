package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;

public interface EventoComSucesso {
    void processa(Compra compra);
}
