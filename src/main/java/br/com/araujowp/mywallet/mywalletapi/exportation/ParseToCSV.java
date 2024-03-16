package br.com.araujowp.mywallet.mywalletapi.exportation;

import java.util.List;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTODet;
import br.com.araujowp.mywallet.mywalletapi.util.UtilDate;
import br.com.araujowp.mywallet.mywalletapi.util.UtilFormat;

public class ParseToCSV {

	
	public String parseMovimentations(List<Movimentation> movimentations) {
		
		StringBuilder csvBuilder = new StringBuilder();
		
		csvBuilder.append("Arquivo").append(";");
		csvBuilder.append("Numero").append(";");
		csvBuilder.append("Data").append(";");
		csvBuilder.append("Operacao").append(";");
		csvBuilder.append("Especificação Titulo").append(";");
		csvBuilder.append("Quantidade").append(";");
        csvBuilder.append("Preço Ajuste").append(";");
        csvBuilder.append("Valor operação").append(";");
        csvBuilder.append("Total taxas").append(";");
        csvBuilder.append("Preco médio").append("\n");
        
        for(Movimentation movimentation : movimentations) {
        	
        	String convertDate = UtilDate.toString(movimentation.getData());
        	
        	csvBuilder.append(movimentation.getNomeArquivo().replace(".PDF", "")).append(";");
        	csvBuilder.append(movimentation.getNumero()).append(";");
        	csvBuilder.append(convertDate).append(";");
        	csvBuilder.append(movimentation.getOperacao()).append(";");
        	csvBuilder.append(movimentation.getEspecificacaoTitulo()).append(";");
        	csvBuilder.append(UtilFormat.toString(movimentation.getQuantidade())).append(";");
        	csvBuilder.append(UtilFormat.toString(movimentation.getPrecoAjuste())).append(";");
        	csvBuilder.append(UtilFormat.toString(movimentation.getValorOperacao())).append(";");
        	csvBuilder.append(UtilFormat.toString(movimentation.getTotalTaxas())).append(";");
        	csvBuilder.append(UtilFormat.toString(movimentation.getPrecoMedio())).append("\n");
        }
        
		return csvBuilder.toString();
	}
	
	public String parseNotes(List<NotaCorretagemDTO> notes) {
		
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
