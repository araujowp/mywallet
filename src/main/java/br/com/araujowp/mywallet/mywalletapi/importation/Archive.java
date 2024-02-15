package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Archive {

	private String directory;

	public Archive(String directory) {
		this.directory = directory;
	}

	public List<String> getAchiveNames() {

		List<String> archiveNames = new ArrayList<>();

		File diretorio = new File(directory);

		if (diretorio != null && diretorio.isDirectory()) {
			File[] arquivos = diretorio.listFiles();
			if (arquivos != null) {
				for (File arquivo : arquivos) {
					if (!arquivo.isDirectory()) {
						if (arquivo.getName().toLowerCase().endsWith(".pdf")) {
							archiveNames.add(arquivo.getName());
						}
					}
				}
			}
		}

		return archiveNames;
	}
	
}
