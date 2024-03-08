package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.exportation.CalculatedOperation;
import br.com.araujowp.mywallet.mywalletapi.exportation.Movimentation;
import br.com.araujowp.mywallet.mywalletapi.exportation.ParseToCSV;
import br.com.araujowp.mywallet.mywalletapi.util.SortNote;

public class ImportNotesPDF {

	public static void main(String[] args) throws IOException {

		List<String> fileNames = findFiles();
		List<NotaCorretagemDTO> notes = getNotes(fileNames);
		List<NotaCorretagemDTO> sortNotes = new SortNote(notes).sort() ;
		List<Movimentation> movimentations = getMovimentations(sortNotes);
		makeCSV(movimentations);
		
	}

	private static void makeCSV(List<Movimentation> movimentations) {
		
		ParseToCSV parse = new ParseToCSV();
		String strMovimentation = parse.parseMovimentations(movimentations);
		Archive.saveTextToFile("c:/teste/movimentations.csv", strMovimentation);
	}

	private static List<String> findFiles() {

		Archive archive = new Archive("C:\\Users\\NB-WAGNER-ARAUJO\\Documents\\declaracao\\notas-corretagem");
//		Archive archive = new Archive("C:\\Users\\NB-WAGNER-ARAUJO\\Documents\\declaracao\\teste2");
		return archive.getAchiveNames("pdf");
	}

	private static List<NotaCorretagemDTO> getNotes(List<String> fileNames) {

		List<NotaCorretagemDTO> notes = new ArrayList<>();
		for (String fileName : fileNames) {

			PDDocument document;
			try {
				System.out.println("arquivo " + fileName);
				document = PDDocument.load(new File(fileName));
				MakeNote makeNote = new MakeNote(document, new ClearLayout());
				notes.addAll(makeNote.getNotes());
				document.close();
			} catch (IOException e) {
				System.out.println("fail to process " + fileName);
				e.printStackTrace();
			}
		}
		return notes;
	}

	private static List<Movimentation> getMovimentations(List<NotaCorretagemDTO> notes) {
		
		List<Movimentation> movimentations = new ArrayList<>();
		for (NotaCorretagemDTO note : notes) {

			CalculatedOperation calculation = new CalculatedOperation(note);
			movimentations.addAll(calculation.builMovimentation());
		}
		return movimentations;
	}

}
