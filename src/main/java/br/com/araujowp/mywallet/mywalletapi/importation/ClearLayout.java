package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class ClearLayout {

//	public static Rectangle2D getHeaderArea() {
//		return new Rectangle2D.Double(410, 0, 150, 70);
//	}
//
//	public static Rectangle2D getClientArea() {
//		return new Rectangle2D.Double(000, 140, 60, 20);
//	}
	
	
	public static  Map<String, Rectangle2D> get(){
		Map<String, Rectangle2D> layout = new HashMap<>();
		
		layout.put(FileldsNote.NUMERO.name(), new Rectangle2D.Double(410, 60, 60, 30));
		layout.put(FileldsNote.DATA_PREGAO.name(), new Rectangle2D.Double(500, 60, 60, 30));
		layout.put(FileldsNote.CLIENTE.name(), new Rectangle2D.Double(000, 140, 60, 20));
		
		return layout;
	} 
	
}

