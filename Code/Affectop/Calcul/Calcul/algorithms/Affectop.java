package Calcul.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class Affectop {
	public Map<Option, LinkedList<Option>> incompatibilites;
	public Map<Student, LinkedList<Option>> availableOptions;
	public ArrayList<Student> students;
	public ArrayList<Option> options;
	public Option recale = new Option(100000000, "recale", -1);
	int nbDays;
	
	void printAffect(){
		for(Student s : students)
			System.out.println(s.mail+":   "+s.affected);
		try{Thread.sleep(500);}catch(InterruptedException e){};
	}
	
	public void makeIncompatibilities() {
		for(Option o : options) {
			incompatibilites.put(o,new LinkedList<Option>());
		}
		incompatibilites.put(recale,new LinkedList<Option>());
		for(Option o1 : options)
			for(Option o2 : options)
				if(o1.day == o2.day || o1.title == o2.title) 
					incompatibilites.get(o1).add(o2);
				
	}
	
	
	public Affectop(ArrayList<Student> students, ArrayList<Option> options, int nbDays) {
		this.incompatibilites = new HashMap<>();
		this.students = students;
		this.options = options;
		this.nbDays = nbDays;

		availableOptions = new HashMap<>();
		makeIncompatibilities();
	}

	private Student getRandom(Option o) {
		return o.accepted.get(Math.abs(new Random().nextInt() % o.accepted.size()));
	}

	private void affect(Student s, Option o) {
		System.out.println("affected options :" +availableOptions.get(s));
		printAffect();
		if (!o.isFull()) { // il y a de la place, tout le monde est content
			s.affected.add(o);
			o.accepted.add(s);
			availableOptions.get(s).removeAll(incompatibilites.get(o));
		} else {
			Student rejected = getRandom(o);
			rejected.affected.remove(o);
			o.accepted.remove(s);
			o.accepted.add(s);
			s.affected.add(o);
			updateChoice(rejected, incompatibilites, availableOptions);
			System.out.println("rejected "+rejected.mail+" options :"+availableOptions.get(rejected));
			System.out.print("\t");
			affect(rejected,availableOptions.get(rejected).getFirst());
			
		}
	}
	
	private  void affectStudent(Student s, int nbOptions) {
		LinkedList<Option> available = availableOptions.get(s);
		while (s.affected.size() < nbOptions) { // tant qu'un étudiant est "célibataire"
			System.out.println(available);
			System.out.println(s.affected);
			if(available == null || available.isEmpty())//si il n'y a plus d'options possibles
				break;
			Option firstChoice = available.get(0); // si erreur ajouter option vide !
			
			affect(s, firstChoice);
		}
	}

	// utilisé juste apres reassign
	private void updateChoice(Student s, Map<Option, LinkedList<Option>> incompatibilites,
			Map<Student, LinkedList<Option>> availableOptions) {
		LinkedList<Option> s_options = availableOptions.get(s);
		
		s_options.clear();
		s_options.addAll(s.preference);
		s_options.add(recale);
		for (Option o : s.affected) {
			s_options.removeAll(incompatibilites.get(o));
		}
	}
	
	public void affectStable() {
		availableOptions = new HashMap<>();
		options.add(recale);
		System.out.println(nbDays);
		for (Student s : students) {
			LinkedList<Option> s_available = new LinkedList<>();
			s_available.addAll(s.preference);
			s_available.add(recale);
			availableOptions.put(s, s_available);
		}
		for (Student s : students) {
			affectStudent(s, nbDays);
		}
	}	
}
 