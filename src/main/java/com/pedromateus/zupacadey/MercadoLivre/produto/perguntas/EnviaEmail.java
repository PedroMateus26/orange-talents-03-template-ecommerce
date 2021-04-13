package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRequestDTO;

public interface EnviaEmail {

    String enviaResposta(PerguntasRequestDTO pergunta);
    String enviaResposta(CompraRequestDTO compra);
}
