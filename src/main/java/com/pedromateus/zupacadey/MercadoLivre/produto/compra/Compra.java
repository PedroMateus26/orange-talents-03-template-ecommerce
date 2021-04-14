package com.pedromateus.zupacadey.MercadoLivre.produto.compra;

import com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.Transacao;
import com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.TransacaoRequest;
import com.pedromateus.zupacadey.MercadoLivre.compraFinalizada.TrasacaoRequestPagSeguroDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.Produto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Transacao> transacoes=new HashSet<>();

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

    public void adicionaTransacao(TransacaoRequest transacaoRequest){
        Transacao transacao=transacaoRequest.convertToTransacao(this);
        Assert.isTrue(!this.transacoes.contains(transacao),"trasacao exestente");
        transacoes.add(transacao);
        this.transacoes.stream().filter(trasacao->transacao.estaConcluida(transacao)).collect(Collectors.toSet());
        Assert.isTrue(!this.transacoes.isEmpty(),"Esta transação já foi concluida");
    }

}
