package br.com.araujowp.mywallet.mywalletapi.exportation;

import java.util.ArrayList;
import java.util.List;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;
import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTODet;

public class CalculatedOperation {

	private NotaCorretagemDTO note;

	public CalculatedOperation(NotaCorretagemDTO note) {
		this.note = note;
	}
	
	public List<Movimentation> builMovimentation(){
		
		List<Movimentation> movimentations = new ArrayList<>();
		
		double totalNote = getTotalNote();
		AveragePrice averagePrice = new AveragePrice();
		
		for(NotaCorretagemDTODet det : note.getDetails()) {
			
			double totalTaxasNote = note.getTaxaLiquidacao() + note.getEmolumentos();
			double relativeWeight = Math.abs(det.getPrecoAjuste()) / totalNote;
			double totalTaxas = relativeWeight * totalTaxasNote;
			
			System.out.println("Trade type " + note.getNumero() + " - " + note.getNomeArquivo());
			TradeType tradeType = TradeType.get(det.getOperacao());
			
			double taxaOperacao = tradeType == TradeType.BUY ? totalTaxas : totalTaxas * -1.0;
			double precoLiquido = det.getPrecoAjuste() + taxaOperacao;
			
			averagePrice.addMovimentation(tradeType, det.getEspecificacaoTitulo(), det.getQuantidade(), precoLiquido);
			double precoMedio = averagePrice.get(det.getEspecificacaoTitulo())  ;
			
			Movimentation movimentation = Movimentation.builder()
					.nomeArquivo(note.getNomeArquivo())
					.numero(note.getNumero())
					.data(note.getDataPregao())
					.operacao(tradeType)
					.mercado(det.getMercado())
					.prazo(det.getPrazo())
					.especificacaoTitulo(det.getEspecificacaoTitulo())
					.obs(det.getObs())
					.quantidade(det.getQuantidade())
					.precoAjuste(det.getPrecoAjuste())
					.valorOperacao(det.getValorOperacao())
					.operacaoFinanceira(det.getOperacaoFinanceira())
					.totalTaxas(totalTaxas)
					.precoMedio(precoMedio)
					.build();
			
			movimentations.add(movimentation);
		}
		
		return movimentations;
	}

	private double getTotalNote() {

		double totalNote = 0;
		try {
			for(NotaCorretagemDTODet det : note.getDetails()) {
				totalNote += det.getPrecoAjuste();
			}			
		}catch(Exception e ) {
			System.out.println("getTotalNote : " + note.getNomeArquivo() + " - " + note.getNumero() );
			e.printStackTrace();
		}
		return totalNote;
	}

}
