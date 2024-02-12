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
import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTODet;

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
		for (int pageNumber = 4; pageNumber < maxPage; pageNumber++) {
			
			stripper.extractRegions(document.getPage(pageNumber));
			
			String numero = getString(stripper, FileldsNote.NUMERO.name());
			
			LocalDate dataPregao;
			dataPregao = UtilDate.getLocalDate(stripper.getTextForRegion(FileldsNote.DATA_PREGAO.name()));
			
			long cliente = Long.valueOf(stripper.getTextForRegion(FileldsNote.CLIENTE.name()).trim());
			
			float taxaLiquidacao = getFloat(stripper, FileldsNote.TAXA_LIQUIDACAO);
			float taxaRegistro  = getFloat(stripper, FileldsNote.TAXA_REGISTRO);
			float emolumentos = getFloat(stripper, FileldsNote.EMOLUMENTOS);
			float irrf = getFloat(stripper, FileldsNote.IRRF);
			
			NotaCorretagemDTO notaDTO = NotaCorretagemDTO.builder()
					.numero(numero)
					.dataPregao(dataPregao)
					.dataInclusao(LocalDate.now())
					.cliente(cliente)
					.taxaLiquidacao(taxaLiquidacao)
					.taxaRegistro(taxaRegistro)
					.emolumentos(emolumentos)
					.IRRF(irrf)
					.build();
			
			for (int line = 0; line <ClearLayout.getCountLine(); line++) {
				
				String operacao = getString(stripper,FieldNoteDetail.OPERACAO, line);
				if (operacao.isBlank()) {
					break;
				}
				
				NotaCorretagemDTODet det = NotaCorretagemDTODet.builder()
						.operacao(operacao)
						.mercado(getString(stripper,FieldNoteDetail.MERCADO, line))
						.prazo(getString(stripper,FieldNoteDetail.PRAZO, line))
						.especificacaoTitulo(getString(stripper,FieldNoteDetail.ESPECIFICACAO_TITULO, line))
						.obs(getString(stripper,FieldNoteDetail.OBS, line))
						.quantidade(getDouble(stripper,FieldNoteDetail.QUANTIDADE, line))
						.precoAjuste(getDouble(stripper,FieldNoteDetail.PRECO_AJUSTE, line))
						.valorOperacao(getDouble(stripper,FieldNoteDetail.VALOR_OPERACAO, line))
						.build();
				
				notaDTO.addDetail(det);
			}
			
			System.out.println(notaDTO.toCSV());
			notas.add(notaDTO);
		}
		document.close();
	}

	private static double getDouble(PDFTextStripperByArea stripper, FieldNoteDetail field, int line) {
		return getDouble(stripper, field.name() + line);
	}

	private static double getDouble(PDFTextStripperByArea stripper, String region) {
		String aux  = stripper.getTextForRegion(region).trim() ; 
		aux = aux.replace(".", "").replace(",", ".");
		aux = aux.replace("\n", "").replace("\r", "");
		return Double.valueOf(aux);
	}

	private static String getString(PDFTextStripperByArea stripper, FieldNoteDetail field, int rowNumber) {
		return getString(stripper, field.name() + rowNumber);
	}
	
	private static String getString(PDFTextStripperByArea stripper, String region) {
		try {
			String aux = stripper.getTextForRegion(region).trim();
			aux = aux.replace(".", "").replace(",", ".");
			return aux;
		}catch(Exception e ) {
			return "";
		}
	}

	private static float getFloat(PDFTextStripperByArea stripper, FileldsNote field) {
		String aux = stripper.getTextForRegion(field.name());
		aux = aux.replaceAll(",", ".");
		return Float.valueOf(aux.trim());
	}
	
}
