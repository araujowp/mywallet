package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class NotaCorretagem {

	
	public static void main(String[] args) throws IOException {
		
	String nota = "C:/teste/pdf/302500_NotaCorretagem-2018-12.pdf";
		
		PDDocument document = PDDocument.load(new File(nota));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				
//		stripper.addRegion("headerNote", ClearLayout.getHeaderArea());   
//		stripper.addRegion("client", ClearLayout.getClientArea() );

		for (Map.Entry<String, Rectangle2D> entry: ClearLayout.get().entrySet()) {
			stripper.addRegion(entry.getKey(), entry.getValue());
		}
		
		int maxPage = document.getNumberOfPages();
		System.out.println("total de paginas: " + maxPage );
		for (int pageNumber = 0; pageNumber < maxPage; pageNumber++) {
//			System.out.println("pagina: " + (pageNumber + 1) );
			
			stripper.extractRegions(document.getPage(pageNumber));
			
			String numero = stripper.getTextForRegion(FileldsNote.NUMERO.name());
			String dataPregao = stripper.getTextForRegion(FileldsNote.DATA_PREGAO.name());
			String cliente = stripper.getTextForRegion(FileldsNote.CLIENTE.name());
			
			System.out.println("numero " + numero.trim());
			System.out.println("dataPregao " + dataPregao.trim());
//			System.out.println(dataPregao);
//			System.out.println("cliente");
//			System.out.println(cliente);
		}
		document.close();
	}
	
}
