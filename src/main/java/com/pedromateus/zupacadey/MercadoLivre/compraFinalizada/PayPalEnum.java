package com.pedromateus.zupacadey.MercadoLivre.compraFinalizada;

public enum PayPalEnum {
    ERRO(0),
    SUCESSO(1);

    PayPalEnum(int i) {
    }

    public static StatusTrasacao normaliza(int status){
        if(status==1){
            return StatusTrasacao.SUCESSO;
        }
        else{
            return StatusTrasacao.FALHA;
        }
    }
}
