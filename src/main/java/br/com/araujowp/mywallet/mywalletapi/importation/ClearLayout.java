package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ClearLayout {

	private int countLine = 19;
	PDFTextStripperByArea stripper;
	
	public ClearLayout() throws IOException {
		stripper = new PDFTextStripperByArea();
		mapFields();
	}
	
	private void mapFields() {
		
		for (Map.Entry<String, Rectangle2D> entry: getNote().entrySet()) {
			stripper.addRegion(entry.getKey(), entry.getValue());
		}

		for (Entry<Integer, Map<String, Rectangle2D>> entryDetail: getDetails().entrySet()) {

			Map<String, Rectangle2D> entryRow = entryDetail.getValue();
			
			for (Map.Entry<String, Rectangle2D> entryField : entryRow.entrySet()) {
				stripper.addRegion(entryField.getKey(), entryField.getValue());
			}
		}
	}
	
	private Map<String, Rectangle2D> getNote() {
		Map<String, Rectangle2D> layout = new HashMap<>();

		layout.put(FileldsNote.NUMERO.name(), new Rectangle2D.Double(410, 60, 60, 30));
		layout.put(FileldsNote.DATA_PREGAO.name(), new Rectangle2D.Double(500, 60, 60, 30));
		layout.put(FileldsNote.CLIENTE.name(), new Rectangle2D.Double(000, 150, 60, 10));
		layout.put(FileldsNote.TAXA_LIQUIDACAO.name(), new Rectangle2D.Double(450, 480, 100, 15));
		layout.put(FileldsNote.TAXA_REGISTRO.name(), new Rectangle2D.Double(450, 495, 100, 5));
		layout.put(FileldsNote.EMOLUMENTOS.name(), new Rectangle2D.Double(450, 540, 100, 5));
		layout.put(FileldsNote.IRRF.name(), new Rectangle2D.Double(450, 610, 100, 5));

		return layout;
	}

	private Map<String, Rectangle2D> getRow(int rowNumber) {

		Map<String, Rectangle2D> detail = new HashMap<>();
		
		double y = 250 + (10 * rowNumber);
		
		detail.put(getName(FieldNoteDetail.OPERACAO, rowNumber), new Rectangle2D.Double(85, y, 17, 10));
		detail.put(getName(FieldNoteDetail.MERCADO, rowNumber), new Rectangle2D.Double(110, y, 60, 10));
		detail.put(getName(FieldNoteDetail.ESPECIFICACAO_TITULO, rowNumber), new Rectangle2D.Double(175, y, 130, 10));
		detail.put(getName(FieldNoteDetail.OBS, rowNumber), new Rectangle2D.Double(290, y, 60, 10));
		detail.put(getName(FieldNoteDetail.QUANTIDADE, rowNumber), new Rectangle2D.Double(350, y, 60, 10));
		detail.put(getName(FieldNoteDetail.PRECO_AJUSTE, rowNumber), new Rectangle2D.Double(410, y, 60, 10));
		detail.put(getName(FieldNoteDetail.VALOR_OPERACAO, rowNumber), new Rectangle2D.Double(470, y, 70, 10));
		detail.put(getName(FieldNoteDetail.OPERACAO_FINANCEIRA, rowNumber), new Rectangle2D.Double(541, y, 50, 10));
		
		return detail;
	}
	
	public PDFTextStripperByArea getStripper() {
		return stripper;
	}
	
	
	private Map<Integer, Map<String, Rectangle2D>> getDetails() {
		Map<Integer, Map<String, Rectangle2D>> details = new HashMap<>();
		
		for(int rowNumber = 0; rowNumber < getCountLine() ; rowNumber++ ) {
			details.put(rowNumber, getRow(rowNumber));
		}
		
		return details;
	}
	
	private String getName(FieldNoteDetail field, int row ) {
		return field.name() + row; 
	}
	
	
	public int getCountLine() {
		return countLine;
	}

}
