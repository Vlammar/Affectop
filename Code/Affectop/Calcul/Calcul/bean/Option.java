package Calcul.bean;

import java.util.LinkedList;

/**
 * Bean
 * 
 * @author Mathieu Vallet
 * @version 2.0
 */
public class Option {
	//la taille de l'option
	public int size;
	//la liste des étudiants
	public LinkedList<Student> accepted;
	//le nom de l'option
	public String intitule;
	//le groupe d'options auquel il appartient (le jour)
	public int group;
	
	/** Constructeur d'option
	 * @param size la taille de l'option
	 * @param intitule le nom de l'option 
	 * @param day l'ensemble d'options auquel il appartient (le jour)
	 * 
	 */
	public Option(int size, String intitule,int day){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.intitule = intitule;
		this.group = day;
	}
	
	/**
	 * @return vrai si l'option est pleine
	 */
	public boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "("+intitule+","+group+","+size+")";
	}
}
