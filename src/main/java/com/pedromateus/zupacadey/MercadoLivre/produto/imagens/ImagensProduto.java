package com.pedromateus.zupacadey.MercadoLivre.produto.imagens;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ImagensProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagem;

    public ImagensProduto() {
    }

    public ImagensProduto(String imagem) {
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public String getImagem() {
        return imagem;
    }
}
