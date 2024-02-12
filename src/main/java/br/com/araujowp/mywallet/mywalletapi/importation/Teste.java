package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Teste {

	public static void main(String[] args) throws IOException {
		
		String nota = "C:/teste/pdf/302500_NotaCorretagem-2018-12.pdf";
		
		PDDocument document = PDDocument.load(new File(nota));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	
		stripper.addRegion("ESPECIFICACAO_TITULO0", new Rectangle2D.Double(90, 250, 10, 10));
		stripper.addRegion("ESPECIFICACAO_TITULO1", new Rectangle2D.Double(90, 260, 10, 10));
		stripper.addRegion("ESPECIFICACAO_TITULO2", new Rectangle2D.Double(90, 270, 10, 10));
		stripper.addRegion("ESPECIFICACAO_TITULO3", new Rectangle2D.Double(90, 280, 10, 10));
		stripper.addRegion("ESPECIFICACAO_TITULO4", new Rectangle2D.Double(90, 290, 10, 10));
		
		
		stripper.extractRegions(document.getPage(4));
		
		String titulo0 = stripper.getTextForRegion("ESPECIFICACAO_TITULO0").trim().replace("\n", "").replace("\r", "");
		String titulo1 = stripper.getTextForRegion("ESPECIFICACAO_TITULO1".trim()).replace("\n", "").replace("\r", "");
		String titulo2 = stripper.getTextForRegion("ESPECIFICACAO_TITULO2".trim()).replace("\n", "").replace("\r", "");
		String titulo3 = stripper.getTextForRegion("ESPECIFICACAO_TITULO3".trim()).replace("\n", "").replace("\r", "");
		String titulo4 = stripper.getTextForRegion("ESPECIFICACAO_TITULO4".trim()).replace("\n", "").replace("\r", "");
		
		System.out.println(" titulo0!" + titulo0.replace("\n", "") + "x"+ titulo0.length());
		System.out.println(" titulo1!" + titulo1.replace("\n", "") + "x" + titulo1.length());
		System.out.println(" titulo2!" + titulo2.replace("\n", "") + "x" + titulo2.length());
		System.out.println(" titulo3!" + titulo3.replace("\n", "") + "x" + titulo3.length());
		System.out.println(" titulo4!" + titulo4.replace("\n", "") + "x" + titulo4.length());
		
		imprimirValorASCII(titulo1.replace("\n", "").replace("\r", "") );
		
	}

	public static void imprimirValorASCII(String str) {
		// Percorre cada caractere da string
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// Imprime o valor ASCII do caractere
			System.out.println("Caractere: " + c + " | Valor ASCII: " + (int) c);
		}
	}
	
}
