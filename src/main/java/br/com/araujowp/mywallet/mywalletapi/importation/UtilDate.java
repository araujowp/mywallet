package br.com.araujowp.mywallet.mywalletapi.importation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilDate {

	public static LocalDate getLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date.trim(), formatter);
	}
	
}
