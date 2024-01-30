package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {

	
	private String nameFile;

	PDFReader(String nameFile){
		this.nameFile = nameFile;
	}
	
    public String read(){
    	
    	String text = "";
    	
        try {
            File pdfFile = new File(nameFile);

            // Carrega o documento PDF
            PDDocument document = PDDocument.load(pdfFile);

            // Instancia um PDFTextStripper para extrair texto
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            // Extrai texto do documento
            text = pdfTextStripper.getText(document);

            // Exibe o texto
            System.out.println(text);

            // Fecha o documento
            document.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return text;
        
    }
	
}
