package Calcul.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Calcul.bean.Option;

/**
 * Bean
 * 
 * @author Mathieu VALLET
 * @version 1.0
 */

public class Student {
	/** 
	 * le mail de l'étudiant
	 */
	private String mail;
	/**
	 * les options ou l'étudiant a été accepté
	 */
	private ArrayList<Option> affected;
	/**
	 * la liste des preferences del'étudiant par ordre decroissant d'interet pour chaques jours 
	 */
	private ArrayList<LinkedList<Option>> preferences;
	
	
	
	private List<Option>  optionValide;
	private String numEtudiant;
	
	
	/**
	 * le constructeur de la classe student
	 * @param mail le mail de l'étudiant
	 * @param preferences les prefereces de l'etudiants par jours
	 * @return un etudiant
	 */
	public Student(String mail,ArrayList<LinkedList<Option>> preferences,String numEtudiant,List<Option>  optionValide) {
		this.mail = mail;
		this.preferences = preferences;
		this.affected = new ArrayList<>();
		this.numEtudiant=numEtudiant;
		this.optionValide=optionValide;
	}
	
	public void setOptionValide(List<Option> optionValide) {
		this.optionValide = optionValide;
	}

	public List<Option> getOptionValide() {
		return optionValide;
	}

	public String getNumEtudiant() {
		return numEtudiant;
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
