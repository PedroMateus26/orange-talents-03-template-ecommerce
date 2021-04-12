package com.pedromateus.zupacadey.MercadoLivre.produto.opiniao;

public class OpiniaoResponseDTO {

        private Integer nota;
        private String titulo;
        private String descricao;

    public OpiniaoResponseDTO(Opiniao entity) {
        this.nota = entity.getNota();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


}
