package br.com.araujowp.mywallet.mywalletapi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.araujowp.mywallet.mywalletapi.dto.notacorretagem.NotaCorretagemDTO;

public class SortNoteTest {

	@Test
	public void testOrder() {

		NotaCorretagemDTO nota1 = NotaCorretagemDTO.builder().dataPregao(UtilDate.getLocalDate("07/03/2025"))
				.numero("1001").build();
		NotaCorretagemDTO nota2 = NotaCorretagemDTO.builder().dataPregao(UtilDate.getLocalDate("07/03/2024"))
				.numero("1012").build();
		NotaCorretagemDTO nota3 = NotaCorretagemDTO.builder().dataPregao(UtilDate.getLocalDate("07/03/2024"))
				.numero("1011").build();
		NotaCorretagemDTO nota4 = NotaCorretagemDTO.builder().dataPregao(UtilDate.getLocalDate("06/03/2024"))
				.numero("3030").build();

		List<NotaCorretagemDTO> notas = new ArrayList<>();
		notas.add(nota1);
		notas.add(nota2);
		notas.add(nota3);
		notas.add(nota4);

		SortNote sortNote = new SortNote(notas);
		List<NotaCorretagemDTO> ordered = sortNote.sort();
		
        assertEquals(UtilDate.getLocalDate("06/03/2024"), ordered.get(0).getDataPregao());
        assertEquals("3030", ordered.get(0).getNumero());

        assertEquals(UtilDate.getLocalDate("07/03/2024"), ordered.get(1).getDataPregao());
        assertEquals("1011", ordered.get(1).getNumero());

        assertEquals(UtilDate.getLocalDate("07/03/2024"), ordered.get(2).getDataPregao());
        assertEquals("1012", ordered.get(2).getNumero());

        assertEquals(UtilDate.getLocalDate("07/03/2025"), ordered.get(3).getDataPregao());
        assertEquals("1001", ordered.get(3).getNumero());
	}

}
