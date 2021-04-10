package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProdutoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.Opiniao;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.OpiniaoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.usuario.UserService;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/products")
public class ProdutoController {

    private ProdutoRepository repository;
    private UserService userService;
    protected Usuario usuario;

    public ProdutoController(ProdutoRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> inserirPoduto(@RequestBody @Valid ProdutoRequestDTO requestDTO){
        Produto produto=requestDTO.convertToProduct(userService);
        produto=repository.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> cadastrandoImagens(@Valid ImagensProdutoRequestDTO imagens, @PathVariable Long id) throws IOException {
        Produto produto=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
        Usuario usuario= userService.usuarioLogado();
        if(usuario.getId()==produto.getUsuario().getId()) {
            ProdutoRequestDTO.salvarImagens(imagens, repository, produto);
            return ResponseEntity.ok().body("Imagens cadastrada com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Transactional
    @PostMapping("/{id}/opiniao")
    public ResponseEntity<?> inserirOpiniao(@Valid @RequestBody OpiniaoRequestDTO opiniaoRequestDTO, @PathVariable Long id){
        Usuario usuario= userService.usuarioLogado();
        Long idProduto = id;
        Produto produto=repository.findById(idProduto).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
        if(usuario!=null){
            produto.addOpinao(opiniaoRequestDTO,usuario);
            produto= repository.save(produto);
            return ResponseEntity.ok().body(opiniaoRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


}
