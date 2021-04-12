package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmailImplementacao implements EnviaEmail{

    @Override
    public String enviaResposta(PerguntasRequestDTO pergunta) {
        return "Nova mensagem recebida!";
    }
}
