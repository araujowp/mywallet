package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class NotaCorretagem {

	
	public static void main(String[] args) throws IOException {
		
	String nota = "C:/teste/pdf/302500_NotaCorretagem-2018-12.pdf";
		
		PDDocument document = PDDocument.load(new File(nota));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				
		stripper.addRegion("headerNote", ClearLayout.getHeaderArea());   
		stripper.addRegion("client", ClearLayout.getClientArea() );

		int maxPage = document.getNumberOfPages();
		System.out.println("total de paginas: " + maxPage );
		for (int pageNumber = 0; pageNumber < maxPage; pageNumber++) {
			System.out.println("pagina: " + (pageNumber + 1) );
			
			stripper.extractRegions(document.getPage(pageNumber));
			
			String headerNote = stripper.getTextForRegion("headerNote");
			String strClient = stripper.getTextForRegion("client");
			System.out.println(headerNote);
			System.out.println(strClient.trim());
		}
		document.close();
	}
	
}
