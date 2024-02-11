package br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem;

import java.time.LocalDate;
import java.util.ArrayList;
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
	List<NotaCorretagemDTODet> detail = new ArrayList<>();

	public void addDetail(NotaCorretagemDTODet itemDet) {
		
		detail = detail == null ? new ArrayList<>() : detail;
		detail.add(itemDet);
	}
	 
    public String toCSV() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(numero).append(",");
        csvBuilder.append(dataPregao).append(",");
        csvBuilder.append(taxaLiquidacao).append(",");
        csvBuilder.append(taxaRegistro).append(",");
        csvBuilder.append(taxaTermoOpcoes).append(",");
        csvBuilder.append(emolumentos).append(",");
        csvBuilder.append(taxaOperacional).append(",");
        csvBuilder.append(execucao).append(",");
        csvBuilder.append(taxaCustodia).append(",");
        csvBuilder.append(impostos).append(",");
        csvBuilder.append(IRRF).append("\n");

        return csvBuilder.toString();
    }
	
}
