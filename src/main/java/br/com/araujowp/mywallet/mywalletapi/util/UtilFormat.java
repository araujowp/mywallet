package br.com.araujowp.mywallet.mywalletapi.util;

import java.text.DecimalFormat;

public class UtilFormat {

	public static String toString(double number) {
        DecimalFormat format = new DecimalFormat("#,###.00");
        String formatNumber = format.format(number);
        return formatNumber;
	}
}
