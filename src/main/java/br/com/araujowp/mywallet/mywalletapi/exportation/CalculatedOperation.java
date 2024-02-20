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
		
		for(NotaCorretagemDTODet det : note.getDetails()) {
			
			double totalTaxasNote = note.getTaxaLiquidacao() + note.getEmolumentos();
			double relativeWeight = Math.abs(det.getPrecoAjuste()) / totalNote;
			double totalTaxas = relativeWeight * totalTaxasNote;
			double precoMedio = 0;
			
			Movimentation movimentation = Movimentation.builder()
					.numero(note.getNumero())
					.operacao(det.getOperacao())
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
		for(NotaCorretagemDTODet det : note.getDetails()) {
			totalNote += det.getPrecoAjuste();
		}
		
		return totalNote;
	}
	
}
