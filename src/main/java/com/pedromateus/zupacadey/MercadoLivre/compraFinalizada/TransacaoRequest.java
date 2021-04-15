package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;

/**
 *
 * @param compra
 * @return retorna uma nova transação em função do gatway recebido
 *
 */
public interface TransacaoRequest {
    Transacao convertToTransacao(Compra compra);
}
