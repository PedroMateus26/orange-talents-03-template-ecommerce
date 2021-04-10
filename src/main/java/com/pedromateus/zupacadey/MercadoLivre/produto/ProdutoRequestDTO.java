package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.categoria.Categoria;
import com.pedromateus.zupacadey.MercadoLivre.produto.caracteristica.CaracteristicasProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProdutoRequestDTO;
import com.pedromateus.zupacadey.MercadoLivre.usuario.UserService;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import com.pedromateus.zupacadey.MercadoLivre.validations.ExistIdValid;
import com.pedromateus.zupacadey.MercadoLivre.validations.UniqueValueValid;

import javax.validation.constraints.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoRequestDTO {

    @UniqueValueValid(domainValue = Produto.class,field = "nome")
    @NotBlank(message = "O campo nome não pode ficar em branco")
    private String nome;

    @NotNull(message = "Ao menos um valor igual ou maior que zero deve ser informado")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "A quantide deve ser informada")
    @PositiveOrZero(message = "A quandidade dever ser um inteiro igual ou maior que zero")
    private Integer quantidade;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000)
    private String descricao;

    @Size(min = 3, message = "A quantide de características deve ser igual ou maior que 3")
    //@UniqueElements(message = "Já existe essa característica salva na base da dados")
    private Set<CaracteristicasProduto> caracteristicas=new HashSet<>();

    @ExistIdValid(clazz = Categoria.class)
    @Positive(message = "O id deve ser maior que zero")
    @NotNull(message = "O id da categoria associada ao produto é obrigatório")
    private Long categoriaId;


    public ProdutoRequestDTO(@NotBlank(message = "O campo nome não pode ficar em branco") String nome, @NotNull(message = "Ao menos um valor igual ou maior que zero deve ser informado") @Positive(message = "O preço deve ser maior que zero") BigDecimal valor, @NotNull(message = "A quantide deve ser informada") @PositiveOrZero(message = "A quandidade dever ser um inteiro igual ou maior que zero") Integer quantidade, @NotBlank(message = "A descrição é obrigatória") @Size(max = 1000) String descricao, Set<CaracteristicasProduto> caracteristicas, @Positive(message = "O id deve ser maior que zero") @NotNull(message = "O id da categoria associada ao produto é obrigatório") Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }


    public Produto convertToProduct(UserService userService) {
        Usuario usuario= userService.usuarioLogado();
        return new Produto(
                this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                usuario,
                this.caracteristicas,
                this.categoriaId

        );
    }

    public  static void salvarImagens(ImagensProdutoRequestDTO imagens,ProdutoRepository repository, Produto produto) throws IOException {
        List<ImagensProduto> list = imagens.getImagens().stream()
                .map(imagem -> new ImagensProduto(imagem.toString()))
                .collect(Collectors.toList());
        produto.addImagens(list);
        repository.save(produto);

    }
}
