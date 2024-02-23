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
    
    public static TradeType get(String value) {
        for (TradeType type : TradeType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("TradeType inválido para o valor: " + value);
    }
    
}