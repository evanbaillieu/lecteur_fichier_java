package main.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FichierText extends Fichier {

	private static final String extention = "txt";
	private File file;
	private List<String> contenue;
	private Boolean charger;

	public FichierText(String name) {
		super(name);
		this.file = new File(name);
		this.contenue = new ArrayList<String>();
		this.charger = false;
	}

	public File getFile() {
		return file;
	}

	public void addcontenue(String line) {
		this.contenue.add(line);
	}

	public List<String> getContenue() {
		return this.contenue;
	}

	public Boolean lu() {
		return this.charger;
	}

	public void charger() {
		this.charger = true;
	}

}
