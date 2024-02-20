package br.com.araujowp.mywallet.mywalletapi.exportation;

import java.util.HashMap;
import java.util.Map;

public class AveragePrice {

	private record Active(double amount, double value) {}
	private Map<String, Active> stock = new HashMap<>();
	
	public double calculate(String operation, String ticker, double amount, double value) {
		
		if(stock.containsKey(ticker)) {
			
			Active positionStock = stock.get(ticker);
			
			if(operation.equals("C")) {
				stock.put(ticker, new Active(positionStock.amount + amount, positionStock.value + value));
			}else {//sale
				double previousAverage = positionStock.value / positionStock.amount; 
				double newAmount = positionStock.amount - amount;
				stock.put(ticker, new Active(newAmount, previousAverage * newAmount));
			}
			
		}else {
			stock.put(ticker, new Active(amount, value));
		}
		
		Active position = stock.get(ticker);
		
		double price  = 0;
		
		if (position.amount == 0 ) {
			price = 0;
		}else {
			price = position.value / position.amount;
		}
		
		return  price;
	}
	
}
