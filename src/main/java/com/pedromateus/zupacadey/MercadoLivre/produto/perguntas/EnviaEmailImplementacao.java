package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailImplementacao implements EnviaEmail{

    @Override
    public String enviaResposta(PerguntasRequestDTO pergunta) {
        return "Nova mensagem recebida!";
    }

    @Override
    public String enviaResposta(CompraRequestDTO compra) {
        return "Seu pagamento foi realizado com sucesso!";
    }


}
