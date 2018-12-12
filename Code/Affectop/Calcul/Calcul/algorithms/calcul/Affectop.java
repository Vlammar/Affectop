package calcul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * La classe chargée de faire l'affectation 
 * @author VALLET Mat
 */
public class Affectop {
	int nbDays;
	ArrayList<Student> students;
	ArrayList<ArrayList<Option>> options;
	Map<Student, ArrayList<Option>> repeaters;
	ArrayList<Student> scatterbrainStudents;
	Map<Option, ArrayList<Option>> incompatibilities;

	
	
	/**
	 * 
	 * @param nbDays le nombre de groupes d'options
	 * @param students les etudiants ayant entre leurs voeux
	 * @param options  les options disponibles
	 * @param repeaters les redoublents
	 * @param scatterbrainStudents les etudiants ayant oublie d'entrer leurs voeux
	 * @param incompatibilities les incompatibilites entre options
	 */
	public Affectop(int nbDays, ArrayList<Student> students, ArrayList<ArrayList<Option>> options,
			Map<Student, ArrayList<Option>> repeaters, ArrayList<Student> scatterbrainStudents,
			Map<Option, ArrayList<Option>> incompatibilities) {
		this.nbDays = nbDays;
		this.students = students;
		this.options = options;
		this.repeaters = repeaters;
		this.scatterbrainStudents = scatterbrainStudents;
		this.incompatibilities = incompatibilities;
	}

	/**
	 * @param 	s l'etudiant dont on va retirer l'option a laquelle il a été affecté qu'il aime le moins
	 * @return	l'option retiree
	 */
	private Option removeOptionLeastPrefered(Student s) {
		int leastFavoriteDay=0;
		int todayRank;
		Option removed;
		for(int d = 0 ; d < s.affected.length ; d++) {
			todayRank = s.preferences.get(d).indexOf(s.affected[d]);
			if(todayRank<0) continue;
			if(s.affected[todayRank] == null)continue;
			if(todayRank > leastFavoriteDay || todayRank == leastFavoriteDay
					&& s.affected[leastFavoriteDay].getAccepted().size() > s.affected[todayRank].getAccepted().size()) {
				leastFavoriteDay = d;
			}
		
		}
		removed = s.affected[leastFavoriteDay];
		s.affected[leastFavoriteDay] = null;
		
		return removed;
	}
	
	/**
	 * @param incompatibilities le dictionnaire des incompatibilites
	 * @param redoublements le dictionnaire des redoublements
	 */
	private void handleRepeaterAffectation(Map<Option, ArrayList<Option>> incompatibilities, Map<Student,ArrayList<Option>> redoublements){
		ArrayList<Option> optionsNotFull = new ArrayList<>();
		for(Student s : redoublements.keySet()) {
			for(int optionRedoublee = 0 ; optionRedoublee < redoublements.get(s).size();optionRedoublee++)
				optionsNotFull.add(removeOptionLeastPrefered(s));
		}
	}

	/**
	 * Affecte les etudiants à une option d'un ensemble d'option en tenant compte des incompatibilités avec les options precedement affectees
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @param d l'index de l'ensemble (le jour) dans lequel sont choisis les options
	 * @param available le dictionnaire des options disponibles  (clé : un(e) etudiant(e), valeur : toutes les options qui lui sont disponibles)
	 */
	private void affectOneDay(Map<Option, ArrayList<Option>> incompatibilities, int d) {
		HashMap<Student, LinkedList<Option>> availableOption = new HashMap<>();
		for(Student s : students) {
			LinkedList<Option> optToday = new LinkedList<Option>(s.preferences.get(d));
			for(int i = 0; i < s.affected.length ; i ++)
				if(s.affected[i] != null)
					optToday.removeAll(incompatibilities.get(s.affected[i]));
			availableOption.put(s,optToday);
		}		
		SimpleAffectop simpAffect = new SimpleAffectop(students,options.get(d),availableOption,d);
		simpAffect.mariagesStable();
		if(!simpAffect.isStable(students)) {
			System.out.println("ERREUR FATALE : PAS STABLE");
		}
	}
	
	/**
	 * Retourne la satisfaction totale de l'affectation courante
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @return la satisfaction de l'affectation
	 */
	private int getSatisfaction() {
		int satisfaction = 0;
		for(Student s : students)
			satisfaction+= s.getSatisfaction();
		satisfaction /= students.size();
		return satisfaction * satisfaction;
	}
	
	/**
	 * Affecte les etudiants à une option par ensemble ensemble d'options en tenant compte des incompatibilités
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @return la satisfaction de l'affectation
	 */
	public int affectStable(Map<Option, ArrayList<Option>> incompatibilities) {		
		ArrayList<Integer> days = new ArrayList<>();
		for(int d = 0 ; d < nbDays; d ++) {
			days.add(d);
			Collections.shuffle(days);
			affectOneDay(incompatibilities, d);
		}
		return getSatisfaction();
	}
	
	/**
	 * Retourne les options disponibles d'un etudiant 	
	 * @param s l'etudiant
	 * @return les options disponibles de l'etudaint en argument
	 */
	ArrayList<ArrayList<Option>> getAvailableOptions(Student s){
		ArrayList<ArrayList<Option>> result = new ArrayList<>();
		for(int d = 0 ; d < s.affected.length ; d ++) {
			ArrayList<Option> todaysOption = new ArrayList<Option>(s.preferences.get(d));
			for(int d_affected = 0 ; d_affected < s.affected.length ; d_affected ++) {
				if(s.affected[d_affected] != null)
					todaysOption.removeAll(incompatibilities.get(s.affected[d_affected]));
			}
			result.add(todaysOption);
		}
		return result;
	}
	
	/**
	 * Launch the algorithm, then handle the repeaters and the scatterbrains
	 * @param students the students
	 * @param options the options
	 * @param incompatibilities the incompatibilities between options
	 * @param repeaters the dictionary of the options that students repeated  
	 * @param scatterbrainStudents students that did not specified their preference
	 * @param nbDays the number of group of options
	 * @param nbLaunch the number of times you launch the algorithm to get the best affect
	 * 
	 * @return the best affect
	 */
	public Map<Student,Option[]> affectTop(int nbDays,
											   int nbLaunch) {
		long bestScore = 0;
		Map<Student,Option[]> affect = new HashMap<>();  
		for(int i = 0 ; i < nbLaunch ;i++) {
			long affectScore = affectStable(incompatibilities);
					
			System.out.println(affectScore+"/"+bestScore);

			if(bestScore < affectScore) {
				bestScore = affectScore;
				affect.clear();
				for(Student s : students) {
					Option[] sOptions = s.affected.clone();
					affect.put(s,sOptions);
				}				
			}
		}

		Map<Student, ArrayList<ArrayList<Option>>> available = new HashMap<>();
		for(Student s : students) {
			available.put(s,getAvailableOptions(s));
		}
		
		
				
		handleRepeaterAffectation(incompatibilities,repeaters);
				
		Reaffector reaff = new Reaffector(scatterbrainStudents, options, nbDays);
		reaff.affect();
		affect.clear();

		for(Student s : students) {
			Option[] sOptions = s.affected.clone();
			affect.put(s,sOptions);
		}

		return affect;
	}
}
