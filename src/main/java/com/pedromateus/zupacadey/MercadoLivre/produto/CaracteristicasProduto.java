package com.pedromateus.zupacadey.MercadoLivre.produto;

import java.util.HashMap;
import java.util.Map;

public class CaracteristicasProduto {

    private String nomeProduto;
    private String valor;
    private String modelo;
    private String garantia;
    private String marca;

    private Map<String,String> outrasCaracteristicas=new HashMap<>();

    public CaracteristicasProduto() {
    }

    public CaracteristicasProduto(String nomeProduto, String valor, String modelo, String garantia, String marca, Map<String, String> outrasCaracteristicas) {
        this.nomeProduto = nomeProduto;
        this.valor = valor;
        this.modelo = modelo;
        this.garantia = garantia;
        this.marca = marca;
        this.outrasCaracteristicas = outrasCaracteristicas;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getValor() {
        return valor;
    }

    public String getModelo() {
        return modelo;
    }

    public String getGarantia() {
        return garantia;
    }

    public String getMarca() {
        return marca;
    }

    public Map<String, String> getOutrasCaracteristicas() {
        return outrasCaracteristicas;
    }
}
