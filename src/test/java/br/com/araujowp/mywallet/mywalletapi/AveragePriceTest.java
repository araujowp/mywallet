package br.com.araujowp.mywallet.mywalletapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.araujowp.mywallet.mywalletapi.exportation.AveragePrice;
import br.com.araujowp.mywallet.mywalletapi.exportation.TradeType;

public class AveragePriceTest {

    private AveragePrice averagePrice;

    @BeforeEach
    public void setUp() {
        averagePrice = new AveragePrice();
    }

    @Test
    public void averagePrice_twoBuys() {
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 100);
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 200);
        assertEquals(15, averagePrice.get("AAPL"));
    }

    @Test
    public void averagePrice_twoBuysWithMoreTicker() {
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 100);
        averagePrice.addMovimentation(TradeType.BUY, "BBB0", 15, 100);
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 200);
        assertEquals(15, averagePrice.get("AAPL"));
    }

    @Test
    public void averagePriceZeroStock() {
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 100);
        averagePrice.addMovimentation(TradeType.SELL, "AAPL",10, 200);
        assertEquals(0, averagePrice.get("AAPL"));
    }

    @Test
    public void averagePriceWithSell() {
        averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 100);
        averagePrice.addMovimentation(TradeType.SELL, "AAPL",5, 200);
        assertEquals(10, averagePrice.get("AAPL"));
    }

    @Test
    public void averagePriceWithRepurchase() {
    	averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 100);
    	averagePrice.addMovimentation(TradeType.SELL, "AAPL",5, 200);
    	averagePrice.addMovimentation(TradeType.BUY, "AAPL", 10, 200);
    	assertEquals(16.66, averagePrice.get("AAPL"), 0.01);
    }
    
    @Test
    public void averagePriceNoStock() {
    	assertEquals(0, averagePrice.get("AAPL"));
    }
    


	
}
