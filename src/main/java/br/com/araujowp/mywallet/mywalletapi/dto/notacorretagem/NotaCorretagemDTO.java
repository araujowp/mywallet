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
	List<NotaCorretagemDTODet> details = new ArrayList<>();

	public void addDetail(NotaCorretagemDTODet itemDet) {
		
		details = details == null ? new ArrayList<>() : details;
		details.add(itemDet);
	}
	 
    public String toCSV() {
    	
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
        
        for(NotaCorretagemDTODet detail : details) {
        	
        	csvBuilder.append(numero).append(";");
        	csvBuilder.append(dataPregao).append(";");

        	csvBuilder.append(detail.getOperacao()).append(";");
        	csvBuilder.append(detail.getMercado()).append(";");
        	csvBuilder.append(detail.getEspecificacaoTitulo()).append(";");
        	csvBuilder.append(detail.getObs()).append(";");
        	csvBuilder.append(detail.getQuantidade()).append(";");
        	csvBuilder.append(detail.getPrecoAjuste()).append(";");
        	csvBuilder.append(detail.getValorOperacao()).append(";");
        	csvBuilder.append(detail.getOperacaoFinanceira()).append(";");
        	
        	csvBuilder.append(taxaLiquidacao).append(";");
        	csvBuilder.append(taxaRegistro).append(";");
        	csvBuilder.append(taxaTermoOpcoes).append(";");
        	csvBuilder.append(emolumentos).append(";");
        	csvBuilder.append(taxaOperacional).append(";");
        	csvBuilder.append(execucao).append(";");
        	csvBuilder.append(taxaCustodia).append(";");
        	csvBuilder.append(impostos).append(";");
        	csvBuilder.append(IRRF).append("\n");
        }

        return csvBuilder.toString();
    }
	
}
