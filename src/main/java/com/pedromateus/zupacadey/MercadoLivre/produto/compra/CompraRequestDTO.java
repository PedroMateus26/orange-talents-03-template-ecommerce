package com.pedromateus.zupacadey.MercadoLivre.produto.compra;

import com.pedromateus.zupacadey.MercadoLivre.produto.Produto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import com.pedromateus.zupacadey.MercadoLivre.validations.ExistIdValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class CompraRequestDTO {
    @ExistIdValid(clazz = Produto.class)
    private Long id;
    @NotNull
    private String formaPagamento;
    @Positive
    @NotNull
    private Integer quantidade;

    public CompraRequestDTO(String formaPagamento, Integer quantidade) {
        this.formaPagamento = formaPagamento;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Compra convertToCompra(Produto produto, Usuario usuario){
        produto.subtraiProduto(this.quantidade);
        FormasDePagamento formasDePagamentoEnum=FormasDePagamento.valueOf(formaPagamento);
        return new Compra(
          formasDePagamentoEnum,
          this.quantidade,
          produto.getValor(),
          produto,
          usuario
        );
    }


}
