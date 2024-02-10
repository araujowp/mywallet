package br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotaCorretagemDTODet {

	private String operacao;
	private String mercado;
	private String prazo;
	private String especificacaoTitulo;
	private String obs;
	private double quantidade; 
	private double precoAjuste;
	private double valorOperacao;
	private String operacaoFinanceira;
}
