package br.com.araujowp.mywallet.mywalletapi.exportation;

public enum TradeType {
    BUY("C"), // compra
    SELL("V"); //Venda
    
    private final String value;
    
    TradeType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}