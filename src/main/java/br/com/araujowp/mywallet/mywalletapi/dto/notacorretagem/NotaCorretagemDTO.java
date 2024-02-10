package br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem;

import java.time.LocalDate;
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
	List<NotaCorretagemDTODet> detail;
}
