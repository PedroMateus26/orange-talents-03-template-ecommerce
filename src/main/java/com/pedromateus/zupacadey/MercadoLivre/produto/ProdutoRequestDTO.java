package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.categoria.Categoria;
import com.pedromateus.zupacadey.MercadoLivre.validations.ExistIdValid;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequestDTO {

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
    private Set<String> caracteristicas=new HashSet<>();

    @ExistIdValid(clazz = Categoria.class)
    @Positive(message = "O id deve ser maior que zero")
    @NotNull(message = "O id da categoria associada ao produto é obrigatório")
    private Long categoriaId;

    public ProdutoRequestDTO(String nome, BigDecimal valor, Integer quantidade, String descricao, Set<String> caracteristicas, Long categoriaId) {
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

    public Set<String> getCaracteristicas() {
        return caracteristicas;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Produto convertToProduct() {
        return new Produto(
                this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                this.caracteristicas,
                this.categoriaId
        );
    }
}
