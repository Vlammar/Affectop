package Calcul;

import java.util.LinkedList;

public class Option {
	//le nombre max d'etudiants pour la matiere
	public int size;
	//la liste des etudiants acceptés
	public LinkedList<Student> accepted;
	//le nom de la matiere
	public String title;
	//le jour de la semaine
	public int day;

	public Option(int size, String title,int day){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.title = title;
		this.day = day;
	}
	
	boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "("+title+","+day+")";
	}
}
