package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;

public interface TransacaoRequest {
    Transacao convertToTransacao(Compra compra);
}
