package com.pedromateus.zupacadey.MercadoLivre.produto.imagens;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ImagensProdutoResponseDTO {
    private List<String> imagens;

    public ImagensProdutoResponseDTO(List<ImagensProduto> imagens) {
        this.imagens = listImagens(imagens);
    }

    public List<String> getImagens() {
        return imagens;
    }

    private List<String> listImagens(List<ImagensProduto> imagens) {
            return imagens.stream()
                .map(ImagensProduto::getImagem)
                .collect(Collectors.toList());
    }
}
