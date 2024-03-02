package br.com.araujowp.mywallet.mywalletapi.importation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Archive {

	private String directory;

	public Archive(String directory) {
		this.directory = directory;
	}

	public List<String> getAchiveNames(String extension) {

		String filter = extension.replace(".", "");
		List<String> archiveNames = new ArrayList<>();

		File diretorio = new File(directory);

		if (diretorio != null && diretorio.isDirectory()) {
			File[] arquivos = diretorio.listFiles();
			if (arquivos != null) {
				for (File arquivo : arquivos) {
					if (!arquivo.isDirectory()) {
						if (arquivo.getName().toLowerCase().endsWith("." + filter)) {
							archiveNames.add(arquivo.getAbsolutePath());
						}
					}
				}
			}
		}

		return archiveNames;
	}

    public static void saveTextToFile(String fileName, String text) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            System.out.println("The text was successfully saved to the file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Error while trying to save the text to the file '" + fileName + "'.");
            e.printStackTrace();
        }
    }
	
}
