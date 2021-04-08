package com.pedromateus.zupacadey.MercadoLivre.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProdutoController {

    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> inserirPoduto(@RequestBody @Valid ProdutoRequestDTO requestDTO){
        Produto produto=requestDTO.convertToProduct();
        produto=repository.save(produto);
        return ResponseEntity.ok().body(produto);
    }
}
