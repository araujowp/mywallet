package br.com.araujowp.mywallet.mywalletapi.importation;

import java.util.List;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTODet;

public class ParseToCSV {

	private List<NotaCorretagemDTO> notes;

	public ParseToCSV(List<NotaCorretagemDTO> notes) {
		this.notes = notes;
	}
	
	public String get() {
		
        StringBuilder csvBuilder = new StringBuilder();

        
        csvBuilder.append("Numero").append(";");
        csvBuilder.append("Data Pregão").append(";");
        
        csvBuilder.append("Operação").append(";");
        csvBuilder.append("Mercado").append(";");
        csvBuilder.append("Especificação Titulo").append(";");
        csvBuilder.append("Obs").append(";");
        csvBuilder.append("Quantidade").append(";");
        csvBuilder.append("Preço Ajuste").append(";");
        csvBuilder.append("Valor operação").append(";");
        csvBuilder.append("Operação Financeira").append(";");
        
        csvBuilder.append("Taxa Liquidacao").append(";");
        csvBuilder.append("Taxa Registro").append(";");
        csvBuilder.append("Taxa Termo Opções").append(";");
        csvBuilder.append("Emolumentos").append(";");
        csvBuilder.append("Taxa Operacional").append(";");
        csvBuilder.append("Execução").append(";");
        csvBuilder.append("Taxa Custodia").append(";");
        csvBuilder.append("Impostos").append(";");
        csvBuilder.append("IRRF").append("\n");
        
        for (NotaCorretagemDTO note : notes) {
        	
        	for(NotaCorretagemDTODet detail : note.getDetails()) {
        		
        		csvBuilder.append(note.getNumero()).append(";");
        		csvBuilder.append(note.getDataPregao()).append(";");
        		
        		csvBuilder.append(detail.getOperacao()).append(";");
        		csvBuilder.append(detail.getMercado()).append(";");
        		csvBuilder.append(detail.getEspecificacaoTitulo()).append(";");
        		csvBuilder.append(detail.getObs()).append(";");
        		csvBuilder.append(detail.getQuantidade()).append(";");
        		csvBuilder.append(detail.getPrecoAjuste()).append(";");
        		csvBuilder.append(detail.getValorOperacao()).append(";");
        		csvBuilder.append(detail.getOperacaoFinanceira()).append(";");
        		
        		csvBuilder.append(note.getTaxaLiquidacao()).append(";");
        		csvBuilder.append(note.getTaxaRegistro()).append(";");
        		csvBuilder.append(note.getTaxaTermoOpcoes()).append(";");
        		csvBuilder.append(note.getEmolumentos()).append(";");
        		csvBuilder.append(note.getTaxaOperacional()).append(";");
        		csvBuilder.append(note.getExecucao()).append(";");
        		csvBuilder.append(note.getTaxaCustodia()).append(";");
        		csvBuilder.append(note.getImpostos()).append(";");
        		csvBuilder.append(note.getIRRF()).append("\n");
        	}
        }

        return csvBuilder.toString();
	}
	
}
