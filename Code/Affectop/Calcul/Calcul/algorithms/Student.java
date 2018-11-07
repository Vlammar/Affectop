package calcul;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe représentant un étudiant 
 * @author VALLET Mat
 */

public class Student {
	/** 
	 * le mail de l'étudiant
	 */
	public String mail;
	/**
	 * les options ou l'étudiant a été accepté
	 */
	public ArrayList<Option> affected;
	/**
	 * la liste des preferences del'étudiant par ordre decroissant d'interet pour chaques jours 
	 */
	public ArrayList<LinkedList<Option>> preferences;
	/**
	 * le constructeur de la classe student
	 * @param mail le mail de l'étudiant
	 * @param preferences les prefereces de l'etudiants par jours
	 * @return un etudiant
	 */
	public Student(String mail,ArrayList<LinkedList<Option>> preferences) {
		this.mail = mail;
		this.preferences = preferences;
		this.affected = new ArrayList<>();
	}
	
	public String toString() {
		return mail+" "+preferences+" ";
	}
	
	
	/**
	 * Donne la satisfaction de l'étudiant
	 * @return la satisfaction
	 */
	public int getSatisfaction() {
		int satis = 0;
		for(int d = 0 ; d < affected.size(); d ++) {
			satis += preferences.get(d).size()-preferences.get(d).indexOf(affected.get(d));
		}
		return satis;
	}
}
