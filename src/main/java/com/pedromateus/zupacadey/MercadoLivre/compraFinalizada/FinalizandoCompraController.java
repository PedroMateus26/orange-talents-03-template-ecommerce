package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "retorno-")
public class FinalizandoCompraController {

    private CompraRepository compraRepository;

    public FinalizandoCompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @PostMapping(value = "pagseguro/{id}")
    public ResponseEntity<?> pagseguro(@RequestBody TransacaoRequest transacaoRequest, @PathVariable Long id) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não há compra com esse id na base de dados."));
        compra.adicionaTransacao(transacaoRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "paypal/{id}")
    public ResponseEntity<?> paypal(@RequestBody TransacaoRequest transacaoRequest, @PathVariable Long id) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não há compra com esse id na base de dados."));
        compra.adicionaTransacao(transacaoRequest);
        return ResponseEntity.ok().build();
    }
}
