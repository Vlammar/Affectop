package calcul;

import java.util.ArrayList;

public class Result implements Comparable<Result>{
	/**
	 * la classe definissant une affectation d'un etudiant a une option.
	 */
	public class Affectation {
		public Student s;
		public ArrayList<Option> options;
		public Affectation(Student s, ArrayList<Option> options) {
			this.s =s;
			this.options = options;
		}
	}
	/**toutes les affectations donnees par l'algorithme.*/
	public ArrayList<Affectation> results;
	public Result(ArrayList<Student> students) {
		results = new ArrayList<>();
		for(Student s : students) {
			ArrayList<Option> options = new ArrayList<>();
			for(int i = 0; i < s.affected.length;i++)
				options.add(s.affected[i]);
			results.add(new Affectation(s, options));
		}
	}
	
	/**
	 * Retourne la satisfaction globale
	 * (somme pour tout etudiant e)(somme pour tout jours j) (index de preference de l'option affectee a e au jour j / nombre d'option disponibles)^2  
	 * @return la satisfaction
	 */
	public int satisfaction() {
		int satisfaction = 0;
		for(Affectation a : results) {
			for(Option opt  : a.s.affected) {
				int sat = (a.s.preferences.get(opt.day).size() - a.s.preferences.get(opt.day).indexOf(opt))/a.s.preferences.get(opt.day).indexOf(opt);
				satisfaction += sat*sat;
			}
		}
		return satisfaction;
	}
	
	@Override
	public int compareTo(Result r) {
		return this.satisfaction()-r.satisfaction();
	}
}