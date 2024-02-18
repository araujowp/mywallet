package br.com.araujowp.mywallet.mywalletapi.exportation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Movimentation {

	private String numero;
	private String operacao;
	private String mercado;
	private String prazo;
	private String especificacaoTitulo;
	private String obs;
	private double quantidade; 
	private double precoAjuste;
	private double valorOperacao;
	private String operacaoFinanceira;
	private double totalTaxas;
	
	
}
