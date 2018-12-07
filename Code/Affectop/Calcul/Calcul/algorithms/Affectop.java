package Calcul.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * La classe chargée de faire l'affectation 
 * @author VALLET Mat
 */
public class Affectop {
	int nbDays;
	ArrayList<Student> students;
	ArrayList<ArrayList<Option>> options;
		
	/**
	 * Constructeur de la classe
	 * @param students les étudiants à affecter
	 * @param options  les options par ensembles
	 * @param nbDays   le nombre d'ensembles d'options
	 * @return le resultat du constructeur
	 */
	public Affectop(ArrayList<Student> students,ArrayList<ArrayList<Option>> options ,int nbDays) {
		this.students = students;
		this.options = options;
		this.nbDays = nbDays;
	}

	/**
	 * Affecte les etudiants à une option d'un ensemble d'option en tenant compte des incompatibilités avec les options precedement affectees
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @param d l'index de l'ensemble (le jour) dans lequel sont choisis les options
	 * @param available le dictionnaire des options disponibles  (clé : un(e) etudiant(e), valeur : toutes les options qui lui sont disponibles)
	 */
	
	public void affectOneDay(HashMap<Option, LinkedList<Option>> incompatibilities, int d,HashMap<Student, ArrayList<LinkedList<Option>>> available ) {
		
		for(Student s : students)
			for(Option o : s.affected)
				available.get(s).get(d).removeAll(incompatibilities.get(o));
		
		SimpleAffectop simpAffect = new SimpleAffectop(students,options.get(d),d);		
		simpAffect.availableOptions.clear();
		
		
		for(Student s : students) {
			simpAffect.availableOptions.put(s,available.get(s).get(d));
		}
		
		simpAffect.mariagesStable();


	}

	/**
	 * Création du dictionnaire des options disponibles pour former les options a partir des préférences 
	 * @return le dictionnaire des options disponibles
	 */
	HashMap<Student, ArrayList<LinkedList<Option>>> copyPreferences() {
		HashMap<Student, ArrayList<LinkedList<Option>>> preferences = new  HashMap<>();

		for(Student s : students) {
			ArrayList<LinkedList<Option>> available = new ArrayList<>();
			
			for(LinkedList<Option> prefOneDay : s.preferences) {
				LinkedList<Option> availableOneDay = new LinkedList<>();
				availableOneDay.addAll(prefOneDay);
				available.add(availableOneDay);
			}
			preferences.put(s, available);
		}
		
		return preferences;
	}
	
	/**
	 * Affecte les etudiants à une option par ensemble ensemble d'options en tenant compte des incompatibilités
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 */

	public void affectStable(HashMap<Option, LinkedList<Option>> incompatibilities) {
		
		LinkedList<Integer> days = new LinkedList<>();
		for(int d = 0 ; d < nbDays; d ++)
			days.add(d);
		Collections.shuffle(days);
		HashMap<Student, ArrayList<LinkedList<Option>>> available = copyPreferences();
		
		for(int d :days) {
			affectOneDay(incompatibilities, d, available);
			//System.out.println(test.AffectopTest.isStable(students, 3,incompatibilities));
		}
	}
	
//======================================================================+TEST A SUPP.	
	
	static ArrayList<Student> randomStudents(int nbStudents, ArrayList<ArrayList<Option>> options, int nbDays){
		ArrayList<Student> result = new ArrayList<>();
		for(int s = 0 ; s < nbStudents; s ++) {
			ArrayList<LinkedList<Option>> preferences = new ArrayList<>();
			for(int d = 0 ; d < nbDays; d ++) {
				LinkedList<Option> choicesToday = new LinkedList<Option>();
				choicesToday.addAll(options.get(d));
				Collections.shuffle(choicesToday);
				preferences.add(choicesToday);
			}
			result.add(new Student("s"+s, preferences));
		}
				
		return result;
	}
	
	// créé une incompatibilité par jour 
	static HashMap<Option, LinkedList<Option>> makeRandomIncompatibilities(ArrayList<ArrayList<Option>> options) {
		HashMap<Option, LinkedList<Option>> incompatibilities = new HashMap<>();
		Random r = new Random();
		for(int d = 0; d < options.size() ; d ++) {
			for(Option opt : options.get(d))
			incompatibilities.put(opt, new LinkedList<>());
		}
		
		for(int d1 = 0; d1 < options.size() ; d1 ++) {
			for(Option opt1 : options.get(d1)) {
				for(int d2 = 0; d2 < options.size() ; d2 ++) {
					if(d1 == d2)
						continue;
					int randIndex = Math.abs(r.nextInt()%(options.get(d2).size()));
					Option opt2 = options.get(d2).get(randIndex);//une option au hasard dans le groupement numéroté par d2
					incompatibilities.get(opt1).add(opt2);//incompatibilités mutuelles
					incompatibilities.get(opt2).add(opt1);//incompatibilités mutuelles
				}
			}
		}
		return incompatibilities;
		
	}
	
	static ArrayList<ArrayList<Option>> randomOptions(int nbDays, int[]minNbOptions,int[]maxNbOptions, int[]minSizeOptions,int[]maxSizeOptions){
		ArrayList<ArrayList<Option>> answers = new ArrayList<ArrayList<Option>>();
		
		Random r = new Random();
		
		for(int d = 0; d < nbDays; d++) {
			ArrayList<Option> todayOptions = new ArrayList<>();
			
			int nbOptionToday = minNbOptions[d]+Math.abs(r.nextInt()%(1+maxNbOptions[d]-minNbOptions[d]));
			for(int o = 0; o < nbOptionToday ; o++) {
				int opSize = minSizeOptions[d]+Math.abs(r.nextInt()%(1+maxSizeOptions[d]-minSizeOptions[d]));
				todayOptions.add(new Option(opSize, "o"+o+"_"+d, d));
			}
			answers.add(todayOptions);
		}
		return answers;
	}
	
	static boolean studentRespectsIncompatibilities(Student s,HashMap<Option,LinkedList<Option>> incompatibilities) {
		for(Option opt :s.affected )
			for(Option compatible :s.affected )
				if(incompatibilities.get(opt).contains(compatible) && opt != compatible) {
					System.out.println("INCOMPATIBILITE");
					System.out.println("opt: "+opt +" "+compatible);
					System.out.println("st : "+s+"   "+s.affected);
					return false;
				}
		return true;
	}
	
	static boolean respectsIncompatibilities(ArrayList<Student> students,HashMap<Option,LinkedList<Option>> incompatibilities) {
		for(Student s : students)
			if(!studentRespectsIncompatibilities(s,incompatibilities))
				return false;
		return true;
	}
	
	static Result getBestResult(int launch,HashMap<Option, LinkedList<Option>> incompatibilities, ArrayList<Student> students, ArrayList<ArrayList<Option>> options,int nbDays) {
		Result best = new Result(students);
		for(int i = 0 ; i < launch ;i++){
			for(Student s : students)
				s .affected.clear();
			for (ArrayList<Option> optionToday : options) {
				for(Option o : optionToday)
					o.accepted.clear();
			}
			Affectop currentAffect = new Affectop(students, options,nbDays);
			currentAffect.affectStable(incompatibilities);
			Result current = new Result(students);
			
			if(current .compareTo(best) > 0)
				best = current;
		}
		return best;
	}

	
	public static void main(String args[]) {
		int nbDays = 3;
		ArrayList<ArrayList<Option>> options= randomOptions(nbDays,new int[]{3,3,3},new int[]{5,5,5},new int[]{1,1,1},new int[]{2,2,2});
		ArrayList<Student> students = randomStudents(20,options, nbDays);
		HashMap<Option, LinkedList<Option>> incompatibilities = makeRandomIncompatibilities(options);
		getBestResult(10,incompatibilities,students,options,nbDays);
		for(Student s : students)
			System.out.println(s.mail+":"+s.affected);
	}
}