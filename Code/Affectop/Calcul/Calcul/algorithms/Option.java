package Calcul.algorithms;

import java.util.LinkedList;

/**
 * Bean
 * 
 * @author Mathieu VALLET
 * @version 1.0
 */
public class Option {
	/**la taille de l'option*/
	private int size;
	/**la liste des etudiants*/
	private LinkedList<Student> accepted;
	/**le nom de l'options*/
	private String title;
	/**le groupe d'options auquel il appartient (le jour)*/
	private int day;
	
	public LinkedList<Student> getAccepted() {
		return accepted;
	}

	public void setAccepted(LinkedList<Student> accepted) {
		this.accepted = accepted;
	}

	public int getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	public int getDay() {
		return day;
	}

	/** Constructeur d'options
	 * @param size la taille de l'option
	 * @param intitule le nom de l'option 
	 * @param day l'ensemble d'options auquel il appartient (le jour)
	 * @return une option
	 */
	public Option(int size, String title,int day){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.title = title;
		this.day = day;
	}
	
	/**
	 * @return vrai si l'option est pleine
	 */
	public boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "("+title+","+day+","+size+")";
	}
}
