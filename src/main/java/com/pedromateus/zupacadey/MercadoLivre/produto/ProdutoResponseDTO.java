package com.pedromateus.zupacadey.MercadoLivre.produto;

import com.pedromateus.zupacadey.MercadoLivre.produto.caracteristica.CaracteristicasProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.imagens.ImagensProduto;
import com.pedromateus.zupacadey.MercadoLivre.produto.opiniao.OpiniaoResponseDTO;
import com.pedromateus.zupacadey.MercadoLivre.produto.perguntas.Pergunta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponseDTO {

    private String nome;
    private String descricao;

    private List<CaracteristicasProduto> caracteristicasProdutos=new ArrayList<>();


    public ProdutoResponseDTO(Produto produto){
        this.nome=produto.getNome();
        this.descricao=produto.getDescricao();
        this.caracteristicasProdutos=listaCaracteristicas(produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CaracteristicasProduto> getCaracteristicasProdutos() {
        return caracteristicasProdutos;
    }

    private List<String> listaPerguntas(Produto produto){
        return produto.getPerguntas().stream()
                .map(pergunta -> new Pergunta(pergunta.getTitulo(), produto.getUsuario(), produto))
                .map(pergunta -> pergunta.getTitulo())
                .collect(Collectors.toList());
    }

    private List<OpiniaoResponseDTO> listaOpiniao(Produto produto){
        return produto.getOpinioes().stream()
                .map(OpiniaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    private List<CaracteristicasProduto> listaCaracteristicas(Produto produto){
        return produto.getCaracteristicas().stream()
                .map(caracteristica -> new CaracteristicasProduto(caracteristica.getNome(),caracteristica.getDescricao()))
                .collect(Collectors.toList());
    }

    private List<ImagensProduto> listaIagens(Produto produto){
        return produto.getImagens().stream()
                .map(imagem -> new ImagensProduto(imagem.getImagem()))
                .collect(Collectors.toList());
    }

    private List<ImagensProduto> listaImagens(Produto produto){
        return produto.getImagens();
    }

    private Double notaTotal(Produto produto){
        return produto.getOpinioes().stream()
                .mapToDouble(opiniao->opiniao.getNota())
                .reduce(0,(total,element)->(total+element));
    }

    private Double notaMedia(Produto produto){
        return notaTotal(produto)/produto.getOpinioes().size();
    }

}
