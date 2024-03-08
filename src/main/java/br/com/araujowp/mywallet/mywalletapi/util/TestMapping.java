package br.com.araujowp.mywallet.mywalletapi.util;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class TestMapping {

	public static void main(String[] args) throws IOException {
		
		String fileName  = "C:/my_file.pdf";
		PDDocument document = PDDocument.load(new File(fileName));
		
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();;
		
		stripper.addRegion("OPERACAO0", new Rectangle2D.Double(90, 250, 15, 10));
		stripper.extractRegions(document.getPage(0));
		String aux = stripper.getTextForRegion("OPERACAO0");
		
		System.out.println("before print");
		System.out.println(aux);
		System.out.println("after print");
		
	}
	
}
