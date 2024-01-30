package br.com.araujowp.mywallet.mywalletapi.importation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeadNote {

	String labelNota ;
	String nota ;
	String labelData;
	String data;
	
	HeadNote(String text){
		String lines[]  = text.split("\\n");
		labelNota = lines[0].trim();
		nota = lines[1].trim();
		labelData = lines[4].trim();
		data = lines[5].trim();
 	}
	
	@Override
	public String toString() {
		return getLabelNota() + " " + getNota() + " " + getLabelData() + " " + getData(); 
	}
	
}
