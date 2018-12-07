package Calcul.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * La classe chargee de faire l'affectation pour un seul ensemble (jour) 
 * @author VALLET Mat
 */

public class SimpleAffectop {
	Map<Student, LinkedList<Option>> availableOptions;
	ArrayList<Student> students;
	ArrayList<Option> options;
	int day;
		
	/**
	 * Constructeur
	 * @param students les etudiants
	 * @param options les options disponible pour l'ensemble
	 * @param day l'index de l'ensemble
	 */
	public SimpleAffectop(ArrayList<Student> students,
			ArrayList<Option> options,int day) {
		availableOptions = new HashMap<>();
		for(Student s : students) {
			LinkedList<Option> available = new LinkedList<>();
			available.addAll(s.preferences.get(day));
			availableOptions.put(s, available);			
		}
		Collections.shuffle(students);
		this.students = students;
		this.options = options;
		
		
	}

	/**
	 * Retire et retourne un etudiant aleatoire dans une liste d'etudiants 
	 * @param students la liste des etudiants
	 * @return un etudiant tire au hasard qui sera retire de la liste donnee en argument
	 */
	Student randomRemove(LinkedList<Student> list) {
		Random r = new Random();
		
		if(list.isEmpty())
			return null;
		int index = Math.abs(r.nextInt()%list.size());
		
		Student removed =list.get(index);
		list.remove(index);
	
		return removed;
	}
	
	/**
	 * Affecte un étudiant à une option (peut avoir une incidence sur l'affectation des autres étudiants si l'option opt est pleine)
	 * @param s l'étudiant 
	 * @param opt l'option
	 */
	void affect(Student s, Option opt) {
		
		if(!opt.isFull()) {
			opt.accepted.add(s);
			s.affected.add(opt);
		}
		else {
			Student desaffected = randomRemove(opt.accepted);
			desaffected.affected.remove(opt);
			
			
			LinkedList<Option> desaffecteOptions = availableOptions.get(desaffected);
			desaffecteOptions.remove(opt);
			
			if(desaffecteOptions.isEmpty()) {
				return;
			}
			affect(desaffected,desaffecteOptions.getFirst());			
		}
	}
	
/**
 * Affecte les etudiants selon l'algorithme du mariage stable 
 * @see https://fr.wikipedia.org/wiki/Algorithme_d%27affectation_de_candidats_apr%C3%A8s_concours_multiples#Algorithme_de_mariage_stable 
 */
	public void mariagesStable(){
		for(Student s : students) {
			LinkedList<Option> preferences = availableOptions.get(s);
			if(preferences.isEmpty()) {
				continue;
			}
			Option firstChoice = preferences.getFirst();

			affect(s,firstChoice);
		
		}
	}
}
