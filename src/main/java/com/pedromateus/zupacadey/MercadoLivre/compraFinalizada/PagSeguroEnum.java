package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

public enum PagSeguroEnum {

    SUCESSO,ERRO;

    public StatusTrasacao normaliza(){
        if(this.equals(SUCESSO)){
            return StatusTrasacao.SUCESSO;
        }else{
            return StatusTrasacao.FALHA;
        }
    }
}
