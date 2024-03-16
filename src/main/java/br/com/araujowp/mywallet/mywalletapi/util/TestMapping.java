package br.com.araujowp.mywallet.mywalletapi.util;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import br.com.araujowp.mywallet.mywalletapi.importation.FieldNoteDetail;

public class TestMapping {

	public static void main(String[] args) throws IOException {
		
//		String fileName  = "C:/my_file.pdf";
		String fileName  = "C:/Users/NB-WAGNER-ARAUJO/Documents/declaracao/notas-corretagem-novas/XPINC_NOTA_NEGOCIACAO_B3_3_2023.pdf";
		PDDocument document = PDDocument.load(new File(fileName));
		
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();;
		
		double top  = 250;
		stripper.addRegion(FieldNoteDetail.OPERACAO.name(), new Rectangle2D.Double(100, top, 15, 10));
		stripper.addRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name(), new Rectangle2D.Double(175, top, 130, 10));
		stripper.extractRegions(document.getPage(1));
		
		String operacao = "";
		String titulo = "";
		
		try{
			
			System.out.println("get operation");
			operacao = stripper.getTextForRegion(FieldNoteDetail.OPERACAO.name());
			System.out.println("get title");
			titulo = stripper.getTextForRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name());
		}catch(Exception e ) {
			System.out.println("fail to get fields");
			e.printStackTrace();
		}
		System.out.println("before print");
		System.out.println(operacao + " - " + titulo);
		System.out.println("after print");
		
	}
	
}
