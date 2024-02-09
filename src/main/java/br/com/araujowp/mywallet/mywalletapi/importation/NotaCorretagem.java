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

		for (Map.Entry<String, Rectangle2D> entry: ClearLayout.getDetail().entrySet()) {
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
			String taxaLiquidacao = stripper.getTextForRegion(FileldsNote.TAXA_LIQUIDACAO.name());
			String taxaRegistro  = stripper.getTextForRegion(FileldsNote.TAXA_REGISTRO.name());
			String emolumentos  = stripper.getTextForRegion(FileldsNote.EMOLUMENTOS.name());
			String irrf  = stripper.getTextForRegion(FileldsNote.IRRF.name());

			String operacao = stripper.getTextForRegion(FieldNoteDetail.OPERACAO.name());
			String mercado = stripper.getTextForRegion(FieldNoteDetail.MERCADO.name());
			String titulo = stripper.getTextForRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name());
			String obs = stripper.getTextForRegion(FieldNoteDetail.OBS.name());
			String quantidade = stripper.getTextForRegion(FieldNoteDetail.QUANTIDADE.name());
			String preco = stripper.getTextForRegion(FieldNoteDetail.PRECO_AJUSTE.name());
			String valor = stripper.getTextForRegion(FieldNoteDetail.VALOR_OPERACAO.name());
			String opFinanceira = stripper.getTextForRegion(FieldNoteDetail.OPERACAO_FINANCEIRA.name());
			
//			System.out.println("numero " + numero.trim());
//			System.out.println("dataPregao " + dataPregao.trim());
//			System.out.println("taxaLiquidacao " + taxaLiquidacao.trim());
//			System.out.println("taxaRegistro " + taxaRegistro.trim());
//			System.out.println("EMOLUMENTOS " + emolumentos.trim());
//			System.out.println("IRRF " + irrf.trim());
//			System.out.println(cliente);
//			System.out.println("operacao " + operacao);
//			System.out.println("mercado " + mercado.trim());
			System.out.println("titulo " + titulo.trim() + " opFinanceira " + opFinanceira.trim());
			
//			if(pageNumber >=  1 ) pageNumber = 6;
		}
		document.close();
	}
	
}
