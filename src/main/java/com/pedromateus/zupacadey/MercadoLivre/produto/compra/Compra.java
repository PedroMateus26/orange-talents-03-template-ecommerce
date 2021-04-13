package com.pedromateus.zupacadey.MercadoLivre.produto.compra;

import com.pedromateus.zupacadey.MercadoLivre.produto.Produto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FormasDePagamento formaPagamento;
    private Integer quantidade;
    private BigDecimal valorUnitario;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public Compra() {
    }

    public Compra(FormasDePagamento formaPagamento, Integer quantidade, BigDecimal valorUnitario, Produto produto, Usuario usuario) {
        this.formaPagamento = formaPagamento;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public FormasDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
