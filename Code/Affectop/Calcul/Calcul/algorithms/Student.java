package Calcul;

import java.util.ArrayList;
import java.util.LinkedList;


public class Student {
	//le mail de l'etudiant
	public String mail;
	//les options ou l'etudiant a ete accepte
	public ArrayList<Option> affected;
	//la liste des preferences de l'etudiant par ordre decroissant d'interet
	public LinkedList<Option> preference;
	
	
	public Student(String mail,LinkedList<Option> preference) {
		this.mail = mail;
		this.preference = preference;
		this.affected = new ArrayList<>();
	}
	
	public String toString() {
		return mail+" "+preference+" ";
	}
	
	//NB je n'aime pas comment cette methode fonctionne, TODO a refaire de facon plus clair
	public int getSatisfaction() {
		int satis = 0;
		for(Option o:affected) {
			satis -= preference.indexOf(o);
			
		}
		return satis;
	}
}
