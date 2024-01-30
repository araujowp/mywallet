package br.com.araujowp.mywallet.mywalletapi.importation;

public class ParseLayout {

	private String text;

	ParseLayout(String text){
		this.text = text;
	}
	
	String[] getLines(){
		return text.split("\\n");
	}
	
}
