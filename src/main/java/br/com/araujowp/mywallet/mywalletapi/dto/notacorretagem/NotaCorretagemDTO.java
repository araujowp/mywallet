package br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotaCorretagemDTO {

	String numero;
	long cliente;
	LocalDate dataInclusao;
	LocalDate dataPregao;
	float taxaLiquidacao;
	float taxaRegistro;
	float taxaTermoOpcoes;
	float taxaANA;
	float emolumentos;
	float taxaOperacional;
	float execucao;
	float taxaCustodia;
	float impostos;
	float IRRF;
	float outros;
	String nomeArquivo;
	List<NotaCorretagemDTODet> details = new ArrayList<>();

	public void addDetail(NotaCorretagemDTODet itemDet) {
		
		details = details == null ? new ArrayList<>() : details;
		details.add(itemDet);
	}

	public void addDetail(List<NotaCorretagemDTODet> details) {
		 for (Iterator<NotaCorretagemDTODet> iterator = details.iterator(); iterator.hasNext();) {
			NotaCorretagemDTODet notaCorretagemDTODet = iterator.next();
			addDetail(notaCorretagemDTODet);
		}
	}
	
}
