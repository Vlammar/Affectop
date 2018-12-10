package Calcul.Calcul.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Option {
	//la taille de l'option
	public int size;
	//la liste des �tudiants
	public LinkedList<Student> accepted;
	//le nom de l'option
	public String nom;
	//le groupe d'options auquel il appartient (le jour)
	public int group;

	private String description;
	private List<String> groupes;
	
	private String mail_prof;

	public String getMail_prof() {
		return mail_prof;
	}

	public void setMail_prof(String mail_prof) {
		this.mail_prof = mail_prof;
	}

	public Option(int size, String nom,int day){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.nom = nom;
		this.group = day;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<String> groupes) {
		this.groupes = groupes;
	}
	
	/**
	 * @return vrai si l'option est pleine
	 */
	public boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "("+nom+","+group+","+size+")";
	}
}