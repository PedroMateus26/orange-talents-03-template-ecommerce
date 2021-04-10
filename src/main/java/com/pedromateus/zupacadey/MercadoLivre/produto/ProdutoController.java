package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProdutoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import com.pedromateus.zupacadey.MercadoLivre.usuario.BuscaUsuarioLogado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/products")
public class ProdutoController {

    private ProdutoRepository repository;
    private BuscaUsuarioLogado buscaUsuarioLogado;
    protected Usuario usuario;

    public ProdutoController(ProdutoRepository repository, BuscaUsuarioLogado buscaUsuarioLogado) {
        this.repository = repository;
        this.buscaUsuarioLogado = buscaUsuarioLogado;
    }

    @PostMapping
    public ResponseEntity<?> inserirPoduto(@RequestBody @Valid ProdutoRequestDTO requestDTO){
        Produto produto=requestDTO.convertToProduct(buscaUsuarioLogado);
        produto=repository.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> cadastrandoImagens(@Valid ImagensProdutoRequestDTO imagens, @PathVariable Long id) throws IOException {
        Produto produto=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
        Usuario usuario= buscaUsuarioLogado.usuarioLogado();
        if(usuario.getId()==produto.getUsuario().getId()) {
            ProdutoRequestDTO.salvarImagens(imagens, repository, produto);
            return ResponseEntity.ok().body("Imagens cadastrada com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }




}
