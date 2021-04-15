package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoComSucesso{
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.concluidaComSucesso(),"Compra não processada");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());

        HttpEntity<Map> entity = new HttpEntity<Map>(request, retornarHeader());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", entity, String.class);
    }

    public HttpHeaders retornarHeader() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTg0ODU1MzUsInVzZXJfbmFtZSI6InBlZHJvQGVtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNVQVJJTyJdLCJqdGkiOiIzNTY0ZjkwMi1lZjEzLTQxZjYtYTYxZC1kYTUwZmM3OGYwYjciLCJjbGllbnRfaWQiOiJBUElNTCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.TQl20wA9zWRdx0hdLTeoNkBc9W3QFILMqEBhu8-f9d0";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }
}
