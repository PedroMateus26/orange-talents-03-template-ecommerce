package com.pedromateus.zupacadey.MercadoLivre.produto.imagens;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImagensProdutoRequestDTO {
    @NotNull
    @Size(min = 1,message = "Deve haver pelo menos um arquivo para ser salvo na base de dados")
    List<MultipartFile> imagens=new ArrayList<>();

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public  List<ImagensProduto> salvarImagens() throws IOException {
        return imagens.stream()
                .map(imagem -> new ImagensProduto(imagem.toString()))
                .collect(Collectors.toList());
    }
}
