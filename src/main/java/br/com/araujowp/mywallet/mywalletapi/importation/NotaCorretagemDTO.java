package br.com.araujowp.mywallet.mywalletapi.importation;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotaCorretagemDTO {

	String numero;
	long cliente;
	LocalDate data_pregao;
	String tipoOperacao;
	float taxaLiquidacao;
	float taxaRegistro;
	float TaxaTermoOpcoes;
	float taxaANA;
	float emolumentos;
	float taxaOperacional;
	float execucao;
	float taxaCustodia;
	float impostos;
	float IRRF;
	float outros;
	String nomeArquivo;

}
