package br.com.araujowp.mywallet.mywalletapi.importation;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;

public class NotaCorretagem {

	
	public static void main(String[] args) throws IOException {
		
	String nota = "C:/teste/pdf/302500_NotaCorretagem-2018-12.pdf";
		
		PDDocument document = PDDocument.load(new File(nota));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				
		for (Map.Entry<String, Rectangle2D> entry: ClearLayout.get().entrySet()) {
			stripper.addRegion(entry.getKey(), entry.getValue());
		}

		for (Entry<Integer, Map<String, Rectangle2D>> entryDetail: ClearLayout.getDetails().entrySet()) {

			Map<String, Rectangle2D> entryRow = entryDetail.getValue();
			
			for (Map.Entry<String, Rectangle2D> entryField : entryRow.entrySet()) {
				stripper.addRegion(entryField.getKey(), entryField.getValue());
			}
		}
		
		List<NotaCorretagemDTO> notas = new ArrayList<>();
		
		int maxPage = document.getNumberOfPages();
		System.out.println("total de paginas: " + maxPage );
		for (int pageNumber = 0; pageNumber < maxPage; pageNumber++) {
			
			stripper.extractRegions(document.getPage(pageNumber));
			
			LocalDate dataPregao;
			dataPregao = UtilDate.getLocalDate(stripper.getTextForRegion(FileldsNote.DATA_PREGAO.name()));
			
			long cliente = Long.valueOf(stripper.getTextForRegion(FileldsNote.CLIENTE.name()));
			
			float taxaLiquidacao = Float.valueOf(stripper.getTextForRegion(FileldsNote.TAXA_LIQUIDACAO.name()));
			float taxaRegistro  = Float.valueOf(stripper.getTextForRegion(FileldsNote.TAXA_REGISTRO.name()));
			float emolumentos =Float.valueOf(stripper.getTextForRegion(FileldsNote.EMOLUMENTOS.name()));
			float irrf = Float.valueOf(stripper.getTextForRegion(FileldsNote.IRRF.name()));
			
			NotaCorretagemDTO notaDTO = NotaCorretagemDTO.builder()
					.numero(stripper.getTextForRegion(FileldsNote.NUMERO.name()).trim())
					.dataPregao(dataPregao)
					.dataInclusao(LocalDate.now())
					.cliente(cliente)
					.taxaLiquidacao(taxaLiquidacao)
					.taxaRegistro(taxaRegistro)
					.emolumentos(emolumentos)
					.IRRF(irrf)
					.build();
			
//			String numero = stripper.getTextForRegion(FileldsNote.NUMERO.name());
//			String dataPregao = stripper.getTextForRegion(FileldsNote.DATA_PREGAO.name());
//			String cliente = stripper.getTextForRegion(FileldsNote.CLIENTE.name());
//			String taxaLiquidacao = stripper.getTextForRegion(FileldsNote.TAXA_LIQUIDACAO.name());
//			String taxaRegistro  = stripper.getTextForRegion(FileldsNote.TAXA_REGISTRO.name());
//			String emolumentos  = stripper.getTextForRegion(FileldsNote.EMOLUMENTOS.name());
//			String irrf  = stripper.getTextForRegion(FileldsNote.IRRF.name());

//			String operacao = stripper.getTextForRegion(FieldNoteDetail.OPERACAO.name());
//			String mercado = stripper.getTextForRegion(FieldNoteDetail.MERCADO.name());
//			String titulo = stripper.getTextForRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name());
//			String obs = stripper.getTextForRegion(FieldNoteDetail.OBS.name());
//			String quantidade = stripper.getTextForRegion(FieldNoteDetail.QUANTIDADE.name());
//			String preco = stripper.getTextForRegion(FieldNoteDetail.PRECO_AJUSTE.name());
//			String valor = stripper.getTextForRegion(FieldNoteDetail.VALOR_OPERACAO.name());
			
//			int line = 0;
//			for (Entry<Integer, Map<String, Rectangle2D>> entryDetail: ClearLayout.getDetails().entrySet()) {
//			
//				Map<String, Rectangle2D> entryRow = entryDetail.getValue();
//				
//				for (Map.Entry<String, Rectangle2D> entryField : entryRow.entrySet()) {
//					stripper.addRegion(entryField.getKey(), entryField.getValue());
//				}
//			}
			
			
//			String titulo0 = stripper.getTextForRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name() + "0");
//			String titulo1 = stripper.getTextForRegion(FieldNoteDetail.ESPECIFICACAO_TITULO.name() + "1");
			
//			String opFinanceira0 = stripper.getTextForRegion(FieldNoteDetail.OPERACAO_FINANCEIRA.name() + "0");
//			String opFinanceira1 = stripper.getTextForRegion(FieldNoteDetail.OPERACAO_FINANCEIRA.name() + "1");
			
//			System.out.println("numero " + numero.trim());
//			System.out.println("dataPregao " + dataPregao.trim());
//			System.out.println("taxaLiquidacao " + taxaLiquidacao.trim());
//			System.out.println("taxaRegistro " + taxaRegistro.trim());
//			System.out.println("EMOLUMENTOS " + emolumentos.trim());
//			System.out.println("IRRF " + irrf.trim());
//			System.out.println(cliente);
//			System.out.println("operacao " + operacao);
//			System.out.println("mercado " + mercado.trim());
//			System.out.println("titulo0 " + titulo0.trim() + " opFinanceira " + opFinanceira0.trim());
//			System.out.println("titulo1 " + titulo1.trim() + " opFinanceira " + opFinanceira1.trim());
			
			notas.add(notaDTO);
//			if(pageNumber >=  1 ) pageNumber = 6;
		}
		document.close();
	}
	
}
