package br.com.araujowp.mywallet.mywalletapi.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UtilFormat {

	public static String toString(double number) {
		
		if (Double.isNaN(number) ) return "";
		
        DecimalFormat format = new DecimalFormat("#,###.00");
        String formatNumber = format.format(number);
        
        BigDecimal decimalValue = new BigDecimal(number);
        BigDecimal integerPart = decimalValue.setScale(0, BigDecimal.ROUND_DOWN);
        
        if (integerPart.compareTo(BigDecimal.ZERO) == 0 )formatNumber = "0" + formatNumber;
        
        return formatNumber;
	}
}
