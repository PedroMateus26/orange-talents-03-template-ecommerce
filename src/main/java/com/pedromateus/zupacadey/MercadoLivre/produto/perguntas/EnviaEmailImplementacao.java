package com.pedromateus.zupacadey.MercadoLivre.produto.perguntas;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailImplementacao{


    public String enviaResposta(PerguntasRequestDTO pergunta) {
        return "Nova mensagem recebida! "+pergunta.getTitulo();
    }


    public String enviaResposta(CompraRequestDTO compra) {
        return "Seu pagamento foi realizado com sucesso!"+ compra.getId();
    }

    public String enviaResposta() {
        return "Sua compra foi concluida com sucesso!";
    }


}
