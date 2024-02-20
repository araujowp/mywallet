package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.exportation.ParseToCSV;

public class ImportNotesPDF {

	
	public static void main(String[] args) throws IOException {
		
	String nota = "C:/teste/pdf/302500_NotaCorretagem-2018-12.pdf";
		
		PDDocument document = PDDocument.load(new File(nota));

		MakeNote makeNote = new MakeNote(document, new ClearLayout());
		List<NotaCorretagemDTO> notas = makeNote.getNotes();
		
		document.close();
		
		ParseToCSV parse = new ParseToCSV();
		
		System.out.println(parse.parseNotes(notas));
	}


	
}
