package main.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import main.metier.*;

public class LecteurFichier {
	private Fichier file;

	public LecteurFichier() {
		// TODO Auto-generated method stub
		Scanner clavier = new Scanner(System.in);
		String nameFile = clavier.nextLine();
		try {
			if (this.determineurDeFichier(nameFile)) {
				if (this.file instanceof FichierText) {
					System.out.println("se ficheir est un texte");
					this.lectureDuFichier();
				} else {
					System.out.println("se fichier est un image");
				}
			} else {
				System.out.println("le fichier nest pas reconnue");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void lectureDuFichier() {
		String choix = "";
		Scanner clavier = new Scanner(System.in);
		do {
			System.out.println(
					"afficher le fichier : \"lire\" \n afficher le fichier avec les caracter inverser : \"lireReverCara\"\n affiche le fichier en comment pas la fin : \" reverse\" \n affiche le fichier en comment pas la fin est les caractere inverser : \" reverseCara\" \n");
			choix = clavier.nextLine();
			switch (choix) {
			case "lire":
				System.out.println("affiche le fichier");
				this.afficheContent();
				break;
			case "lireReverCara":
				System.out.println("affiche le fichier avec les carater inverser");
				this.afficheContentCara();
				break;
			case "reverse":
				System.out.println("affiche l'inverse");
				this.reverseContent();
				break;
			case "reverseCara":
				System.out.println("affiche l'inverse cara");
				this.reverseContentCara();
				break;
			default:
				System.out.println("exit pour sortir");
			}
		} while (choix != "exit");
	}

	public void reverseContent() {
		int size = ((FichierText) this.file).getContenue().size();
		if (this.file.lu()) {
			for (int taille = size; taille > 0; taille--) {
				System.out.println(((FichierText) this.file).getContenue().get(taille - 1));
			}
		} else {
			System.out.println("doit etre afficher normalment avant");
		}
	}

	public void reverseContentCara() {
		int size = ((FichierText) this.file).getContenue().size();
		if (this.file.lu()) {
			for (int taille = size; taille > 0; taille--) {
				StringBuilder strb = new StringBuilder(((FichierText) this.file).getContenue().get(taille - 1));
				System.out.println(strb.reverse().toString());
			}
		} else {
			System.out.println("doit etre afficher normalment avant");
		}
	}

	public void afficheContentCara() {
		if (this.file.lu()) {
			((FichierText) this.file).getContenue().forEach((line) -> {
				StringBuilder strb = new StringBuilder(line);
				System.out.println(strb.reverse().toString());
			});
		} else {
			System.out.println("doit etre afficher normalment avant");
		}
	}

	public void afficheContent() {
		if (!this.file.lu()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file.getFile())));
				for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					System.out.println(line);
					((FichierText) this.file).addcontenue(line);
				}
				((FichierText) this.file).charger();
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			((FichierText) this.file).getContenue().forEach((line) -> {
				System.out.println(line);
			});
		}

	}

	public boolean determineurDeFichier(String nom) {
		System.out.println(nom);
		int i = nom.lastIndexOf('.');
		String fe = "";
		if (i > 0) {
			fe = nom.substring(i + 1);
		}
		switch (fe) {
		case "txt":
			this.file = new FichierText(nom);
			return true;
		case "png":
			this.file = new FichierPng(nom);
			return true;
		default:
			return false;
		}
	}
}
