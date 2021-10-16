package main.metier;

import java.io.File;

public abstract class Fichier {
	private String nameFile;

	public Fichier(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public abstract File getFile();

	public abstract Boolean lu();
}
