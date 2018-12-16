package Calcul.Calcul.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Student {
	private String nom;
	private String prenom;
	public int numEtu;

	private Promotion promotion;
	/** 
	 * le mail de l'�tudiant
	 */
	public String mail;
	/**
	 * les options o� l'�tudiant a �t� accept�
	 */
	public Option [] affected;

	/**
	 * la liste des preferences de l'�tudiant par ordre d�croissant d'interet pour chaques jours 
	 */
	public ArrayList<LinkedList<Option>> preferences;
	
	private List<Option>  optionValid�;	
	private List<Option>  optionPref; // a enlever
	
	private String numEtudiant;
	
	public int nbDays;

	
	/**
	 * le constructeur de la classe student
	 * @param mail le mail de l'�tudiant
	 * @param preferences les prefereces de l'etudiants par jours
	 * @return un etudiant
	 */
	public Student(String mail,ArrayList<LinkedList<Option>> preferences,int nbDays, int numEtu) {
		this.mail = mail;
		this.preferences = preferences;
		this.affected = new Option[nbDays];
		this.numEtu = numEtu;
	}
	public Student(String mail,ArrayList<LinkedList<Option>> preferences,int nbDays) {
		this.mail = mail;
		this.preferences = preferences;
		this.affected = new Option[nbDays];
		this.numEtu = 0;
	}
	/*
	public Eleve(String nom, String prenom, String adresseMail, Promotion promotion, List<Option> optionValid�,
			String numEtudiant) {
		super(nom, prenom, adresseMail);
		this.promotion = promotion;
		if(optionValid�==null)
			optionValid�=new ArrayList<>();
		this.optionValid� = optionValid�;
		this.numEtudiant = numEtudiant;
	}*/
	public String toString() {
		return nom + " " +prenom + " "  + mail +" ";
	}
	/**
	 * Donne la satisfaction de l'�tudiant d�finie par
	 * s = somme pour tous les jours de l'indice de l'option affect�e ce jour l� 
	 * @return la satisfaction
	 */
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setNomPrenom(String nom, String prenom) {
		this.prenom = prenom;
		this.nom = nom;
	}
	public int getSatisfaction() {
		int satis = 0;
		for(int d = 0 ; d < affected.length; d ++) {
			satis += preferences.get(d).size();
			satis -= preferences.get(d).indexOf(affected[d]);
		}
		return satis * satis;
	}
	
	public LinkedList<Option> getPreferences(int d) {
		return this.preferences.get(d);
	}
	
	/**
	 * @param opt
	 * @return true if the student is affected to this option
	 */
	public boolean isAffectedTo(Option opt) {
		return affected[opt.day] == opt;
	}
	
	/**
	 * remove all affected option from this student
	 */
	public void clearAffected() {
		for(int d = 0; d < nbDays; d++)
			affected[d] = null;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<Option> getOptionValid�() {
		return optionValid�;
	}

	public void setOptionValid�(List<Option> optionValid�) {
		this.optionValid� = optionValid�;
	}
	
	public List<Option> getOptionPref() {
		return optionPref;
	}

	public void setOptionPref(List<Option> optionPref) {
		this.optionPref = optionPref;
	}

	public String getNumEtudiant() {
		return numEtudiant;
	}

	public void setNumEtudiant(String numEtudiant) {
		this.numEtudiant = numEtudiant;
	}
}
