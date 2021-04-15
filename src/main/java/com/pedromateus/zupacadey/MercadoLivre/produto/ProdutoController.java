package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.exceptions.personalizadas.FormaPamentoInvalidaException;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRepository;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.CompraRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.compra.FormasDePagamento;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProdutoController {

    private ProdutoRepository repository;
    private UserService userService;
    protected Usuario usuario;
    private CompraRepository compraRepository;

    @Autowired
    private EnviaEmailImplementacao email;

    public ProdutoController(ProdutoRepository repository, UserService userService, CompraRepository compraRepository) {
        this.repository = repository;
        this.userService = userService;
        this.compraRepository = compraRepository;
    }

    @PostMapping
    public ResponseEntity<?> inserirPoduto(@RequestBody @Valid ProdutoRequestDTO requestDTO){
        Produto produto=requestDTO.convertToProduct(userService);
        produto=repository.save(produto);
        return ResponseEntity.ok().body(new ProdutoResponseDTO(produto));
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
        ProdutoResponseDetalhesDTO produtoProntoResponse=new ProdutoResponseDetalhesDTO(produto);
        return ResponseEntity.ok().body(produtoProntoResponse);
    }

    @Transactional
    @PostMapping("/compra")
    public ResponseEntity<?> realizarCompra(@RequestBody CompraRequestDTO compraRequestDTO){
        Usuario usuario= userService.usuarioLogado();
        try{
            FormasDePagamento.metodoDePagamentoValido(compraRequestDTO.getFormaPagamento());
        }catch (IllegalArgumentException e){
            throw new FormaPamentoInvalidaException("Forma de pagamento não permitida");
        }
            Produto produto = repository.findById(compraRequestDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Id do produto não consta na base de dados."));
            if(compraRequestDTO.getQuantidade()>produto.getQuantidade()){
                Assert.isTrue(compraRequestDTO.getQuantidade()>produto.getQuantidade(), "Não há produtos suficiente no estoque");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else{
                Compra compra = compraRequestDTO.convertToCompra(produto,usuario);
                compraRepository.save(compra);
                System.out.println(email.enviaResposta(compraRequestDTO));
                return ResponseEntity.status(303).body(FormasDePagamento.valueOf(compraRequestDTO.getFormaPagamento()).gatwaysPagamento(compra));
            }


        }
    }

