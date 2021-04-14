package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

import com.pedromateus.zupacadey.MercadoLivre.produto.compra.Compra;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Transacao {
    @Id
    private Long id;
    @ManyToOne
    private Compra compra;
    @CreationTimestamp
    private Instant createAt=Instant.now();
    @Enumerated(EnumType.STRING)
    private StatusTrasacao statusTrasacao;

    public Transacao() {
    }

    public Transacao(Long id, Compra compra, StatusTrasacao statusTrasacao) {
        this.id = id;
        this.compra = compra;
        this.statusTrasacao = statusTrasacao;
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public StatusTrasacao getStatusTrasacao() {
        return statusTrasacao;
    }

    public boolean estaConcluida(Transacao transacao) {
        return this.statusTrasacao.equals(StatusTrasacao.SUCESSO);
    }
}
