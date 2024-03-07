package br.com.araujowp.mywallet.mywalletapi.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;

public class SortNote {
	
	private List<NotaCorretagemDTO> notes;

	public SortNote(List<NotaCorretagemDTO> notes) {
		this.notes = notes;
	}

	public List<NotaCorretagemDTO> sort() {
		Collections.sort(notes, new Comparator<NotaCorretagemDTO>() {
			@Override
			public int compare(NotaCorretagemDTO nota1, NotaCorretagemDTO nota2) {
				int compareDataPregao = nota1.getDataPregao().compareTo(nota2.getDataPregao());
				if (compareDataPregao != 0) {
					return compareDataPregao;
				}
				return nota1.getNumero().compareTo(nota2.getNumero());
			}
		});
		return notes;
	}
	
}
