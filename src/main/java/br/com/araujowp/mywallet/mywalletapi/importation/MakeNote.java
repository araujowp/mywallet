package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTODet;

public class MakeNote {

	private ClearLayout layout;
	private PDDocument document;
	private PDFTextStripperByArea stripper;

	public MakeNote(PDDocument document, ClearLayout layout) {
		this.document = document;
		this.layout = layout;
	}
	
	private double getDouble(FieldNoteDetail field, int line) {
		return getDouble(field.name() + line);
	}

	private double getDouble(String region) {
		String aux  = stripper.getTextForRegion(region).trim() ; 
		aux = aux.replace(".", "").replace(",", ".");
		aux = aux.replace("\n", "").replace("\r", "");
		return Double.valueOf(aux);
	}

	private String getString(FieldNoteDetail field, int rowNumber) {
		return getString(field.name() + rowNumber);
	}
	
	private String getString(String region) {
		try {
			String aux = stripper.getTextForRegion(region).trim();
			aux = aux.replace(".", "").replace(",", ".");
			return aux;
		}catch(Exception e ) {
			return "";
		}
	}

	private float getFloat(FileldsNote field) {
		String aux = stripper.getTextForRegion(field.name());
		aux = aux.replaceAll(",", ".");
		return Float.valueOf(aux.trim());
	}
	
	public List<NotaCorretagemDTO> getNotes() throws IOException{
		
		stripper = layout.getStripper();
		List<NotaCorretagemDTO> notes = new ArrayList<>();
		
		int maxPage = document.getNumberOfPages();
		for (int pageNumber = 0; pageNumber < maxPage; pageNumber++) {
			
			stripper.extractRegions(document.getPage(pageNumber));
			
			String numero = getString(FileldsNote.NUMERO.name());
			
			LocalDate dataPregao;
			dataPregao = UtilDate.getLocalDate(stripper.getTextForRegion(FileldsNote.DATA_PREGAO.name()));
			
			long cliente = Long.valueOf(stripper.getTextForRegion(FileldsNote.CLIENTE.name()).trim());
			
			float taxaLiquidacao = getFloat(FileldsNote.TAXA_LIQUIDACAO);
			float taxaRegistro  = getFloat(FileldsNote.TAXA_REGISTRO);
			float emolumentos = getFloat(FileldsNote.EMOLUMENTOS);
			float irrf = getFloat(FileldsNote.IRRF);
			
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
			
			for (int line = 0; line <layout.getCountLine(); line++) {
				
				String operacao = getString(FieldNoteDetail.OPERACAO, line);
				if (operacao.isBlank()) {
					break;
				}
				
				NotaCorretagemDTODet det = NotaCorretagemDTODet.builder()
						.operacao(operacao)
						.mercado(getString(FieldNoteDetail.MERCADO, line))
						.prazo(getString(FieldNoteDetail.PRAZO, line))
						.especificacaoTitulo(getString(FieldNoteDetail.ESPECIFICACAO_TITULO, line))
						.obs(getString(FieldNoteDetail.OBS, line))
						.quantidade(getDouble(FieldNoteDetail.QUANTIDADE, line))
						.precoAjuste(getDouble(FieldNoteDetail.PRECO_AJUSTE, line))
						.valorOperacao(getDouble(FieldNoteDetail.VALOR_OPERACAO, line))
						.build();
				
				notaDTO.addDetail(det);
			}
			
			notes.add(notaDTO);
		}
		return notes;
	}
}
