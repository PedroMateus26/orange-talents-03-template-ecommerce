package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProdutoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProdutoResponseDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.Opiniao;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.OpiniaoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.OpiniaoResponseDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.perguntas.EnviaEmailImplementacao;
import com.pedromateus.zupacadey.MercadoLivre.produto.perguntas.Pergunta;
import com.pedromateus.zupacadey.MercadoLivre.produto.perguntas.PerguntasRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.usuario.UserService;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProdutoController {

    private ProdutoRepository repository;
    private UserService userService;
    protected Usuario usuario;

    @Autowired
    private EnviaEmailImplementacao email;

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
        if(usuario.getId().equals(produto.getUsuario().getId())) {
            List<ImagensProduto>list=imagens.salvarImagens();
            produto.addImagens(list);
            repository.save(produto);
            return ResponseEntity.ok().body(new ImagensProdutoResponseDTO(list));
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
            Opiniao opiniao=opiniaoRequestDTO.convertToOpiniao(opiniaoRequestDTO,usuario, produto);
            produto.addOpinao(opiniao);
            repository.save(produto);
            return ResponseEntity.ok().body(new OpiniaoResponseDTO(opiniao));
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @Transactional
    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> inserirPergunta(@Valid @RequestBody PerguntasRequestDTO pergutasRequestDTO, @PathVariable Long id){
        Usuario usuario= userService.usuarioLogado();
        Long idProduto = id;
        Produto produto=repository.findById(idProduto).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
        if(usuario!=null){
            System.out.println(email.enviaResposta(pergutasRequestDTO));
            Pergunta pergunta=pergutasRequestDTO.convertToPergunta(usuario,produto);
            produto.addPergunta(pergunta);
            repository.save(produto);
            return ResponseEntity.ok().body(pergutasRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> retornaProduto(@PathVariable Long id){
        Produto produto = repository.findById(id).orElseThrow(()->new EntityNotFoundException("Produto não econtrado na base de dados."));
        ProdutoResponseDTO produtoProntoResponse=new ProdutoResponseDTO(produto);
        return ResponseEntity.ok().body(produtoProntoResponse);
    }
}
