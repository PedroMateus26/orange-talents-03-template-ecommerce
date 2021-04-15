package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.retornocompra.*;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class FinalizandoCompraController {

    private CompraRepository compraRepository;
    private Set<EventoComSucesso> eventoComSucessoSet=new HashSet<>();

    public FinalizandoCompraController(CompraRepository compraRepository, Set<EventoComSucesso> eventoComSucessoSet) {
        this.compraRepository = compraRepository;
        this.eventoComSucessoSet = eventoComSucessoSet;
    }

    @PostMapping("retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> retornoPagSeguroDTO(@PathVariable Long id,
                                 @Valid TrasacaoRequestPagSeguroDTO request) {
        return processaPagamento(id, request);
    }


    @PostMapping("retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> retornoPaypal(@PathVariable Long id, TrasacaoRequestPayPalDTO request) {

        return processaPagamento(id, request);
    }


    private ResponseEntity<?> processaPagamento(Long id, TransacaoRequest request){

        Compra compra = compraRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Compra não encontrada."));

        compra.adicionaTransacao(request);

        if(compra.concluidaComSucesso()){
            eventoComSucessoSet.stream().forEach(elemeto->elemeto.processa(compra));
        }

        return ResponseEntity.ok().body(request);
    }

    @PostMapping(value = "/notas-fiscais")
    public void notasFiscais(@RequestBody NotaFiscalRequetsDTO notaFiscalRequetsDTO){
        System.out.println("Imprimindo notas fiscais  "+notaFiscalRequetsDTO.toString());
    }

    @PostMapping(value = "/ranking")
    public void ranking(@RequestBody RankingRequetsDTO rankingRequetsDTO){
        System.out.println("Imprimindo ranking "+rankingRequetsDTO.toString());
    }

    @PostMapping(value = "/email-resposta")
    public void emailResposta(@RequestBody EmailRequetsDTO emailRequetsDTO){
        System.out.println("Enviando email "+emailRequetsDTO.toString());
    }

}
