package calcul;

import java.util.LinkedList;

/**
 * La classe correspondant à une option
 */
public class Option {
	/**la taille de l'option*/
	public int size;
	/**la liste des étudiants*/
	public LinkedList<Student> accepted;
	/**le nom de l'options*/
	public String intitule;
	/**le groupe d'options auquel il appartient (le jour)*/
	public int day;
	
	/** Constructeur d'options
	 * @param size la taille de l'option
	 * @param intitule le nom de l'option 
	 * @param day l'ensemble d'options auquel il appartient (le jour)
	 * @return une option
	 */
	public Option(int size, String intitule,int day){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.intitule = intitule;
		this.day = day;
	}
	
	/**
	 * @return vrai si l'option est pleine
	 */
	public boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "("+intitule+","+day+","+size+")";
	}
}
