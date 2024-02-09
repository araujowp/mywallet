package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class ClearLayout {

	public static Map<String, Rectangle2D> get() {
		Map<String, Rectangle2D> layout = new HashMap<>();

		layout.put(FileldsNote.NUMERO.name(), new Rectangle2D.Double(410, 60, 60, 30));
		layout.put(FileldsNote.DATA_PREGAO.name(), new Rectangle2D.Double(500, 60, 60, 30));
		layout.put(FileldsNote.CLIENTE.name(), new Rectangle2D.Double(000, 140, 60, 20));
		layout.put(FileldsNote.TAXA_LIQUIDACAO.name(), new Rectangle2D.Double(450, 480, 100, 15));
		layout.put(FileldsNote.TAXA_REGISTRO.name(), new Rectangle2D.Double(450, 495, 100, 5));
		layout.put(FileldsNote.EMOLUMENTOS.name(), new Rectangle2D.Double(450, 540, 100, 5));
		layout.put(FileldsNote.IRRF.name(), new Rectangle2D.Double(450, 610, 100, 5));

		return layout;
	}

	public static Map<String, Rectangle2D> getDetail() {
		Map<String, Rectangle2D> detail = new HashMap<>();
		
		detail.put(FieldNoteDetail.OPERACAO.name(), new Rectangle2D.Double(90, 250, 10, 20));
		detail.put(FieldNoteDetail.MERCADO.name(), new Rectangle2D.Double(100, 250, 60, 20));
		detail.put(FieldNoteDetail.ESPECIFICACAO_TITULO.name(), new Rectangle2D.Double(160, 250, 130, 10));
		detail.put(FieldNoteDetail.OBS.name(), new Rectangle2D.Double(290, 250, 60, 10));
		detail.put(FieldNoteDetail.QUANTIDADE.name(), new Rectangle2D.Double(350, 250, 60, 10));
		detail.put(FieldNoteDetail.PRECO_AJUSTE.name(), new Rectangle2D.Double(410, 250, 60, 10));
		detail.put(FieldNoteDetail.VALOR_OPERACAO.name(), new Rectangle2D.Double(470, 250, 70, 10));
		detail.put(FieldNoteDetail.OPERACAO_FINANCEIRA.name(), new Rectangle2D.Double(541, 250, 50, 10));
		
		return detail;
	}

}
