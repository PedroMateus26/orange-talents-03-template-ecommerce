package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.produto.caracteristica.CaracteristicasProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProduto;
import com.pedromateus.zupacadey.MercadoLivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    private Long categoriaId;

    @CreationTimestamp
    private Instant createdAt=Instant.now();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<CaracteristicasProduto> caracteristicas=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ImagensProduto> imagens=new ArrayList<>();



    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Usuario usuario, Set<CaracteristicasProduto> caracteristicas, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.usuario = usuario;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public List<ImagensProduto> getImagens() {
        return imagens;
    }

    public void addImagens(List<ImagensProduto> imagem) {
        imagens.addAll(imagem);
    }

}
