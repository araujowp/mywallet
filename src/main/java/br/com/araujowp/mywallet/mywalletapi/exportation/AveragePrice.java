package br.com.araujowp.mywallet.mywalletapi.exportation;

import java.util.HashMap;
import java.util.Map;

public class AveragePrice {

	private record StockItem(double amount, double value) {}
	private Map<String, StockItem> stock = new HashMap<>();
	
	public void addMovimentation(TradeType type, String ticker, double amount, double value) {
		
		if(stock.containsKey(ticker)) {
			
			StockItem stockItem = stock.get(ticker);
			
			if(type == TradeType.BUY) {
				stock.put(ticker, new StockItem(stockItem.amount + amount, stockItem.value + value));
			}else {
				double previousAverage = stockItem.value / stockItem.amount; 
				double newAmount = stockItem.amount - amount;
				stock.put(ticker, new StockItem(newAmount, previousAverage * newAmount));
			}
			
		}else {
			stock.put(ticker, new StockItem(amount, value));
		}
	}
	
	public double get(String ticker) {

		if (!stock.containsKey(ticker)) return 0;
		
		StockItem stockItem = stock.get(ticker);
		
		if (stockItem.amount == 0 && stockItem.value == 0) {
			return 0;
		}else {
			return stockItem.value / stockItem.amount;
		}
	}
	
}
