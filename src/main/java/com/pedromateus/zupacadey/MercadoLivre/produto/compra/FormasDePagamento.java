package com.pedromateus.zupacadey.MercadoLivre.produto.compra;

public enum FormasDePagamento implements GatwaysDePagamento{
    PAYPAL("paypal"){
        @Override
        public String gatwaysPagamento(Compra compra) {
            return "paypal.com?buyerId={"+compra.getId()+"}&redirectUrl={urlRetornoAppPosPagamento}";
        }
    },

    PAGSEGURO("pagseguro"){
        @Override
        public String gatwaysPagamento(Compra compra) {
            return "pagseguro.com?returnId={"+compra.getId()+"}&redirectUrl={urlRetornoAppPosPagamento}";
        }
    };

    private String descricao;

    FormasDePagamento(String gatway) {
        this.descricao=getDescricao();
    }

    public String getDescricao(){
        return this.descricao;
    }

    public static void metodoDePagamentoValido(String metodoPagamento){
        /**
         * @Param: Verifica se a forma de pagamento existe dentro do enum.
         * Caso não exista uma exceção é retornada.
         */
        FormasDePagamento.valueOf(metodoPagamento);
    }
}
