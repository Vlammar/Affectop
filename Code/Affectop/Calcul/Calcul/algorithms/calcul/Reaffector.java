package calcul;

import java.util.ArrayList;

public class Reaffector {
	/**les etudiants n'ayant pas encore renseigne leurs preferences*/
	ArrayList<Student> scatterbrainStudent;
	/** les options disponibles (groupees par groupe d'option)*/
	ArrayList<ArrayList<Option>> options;
	/**le nombre de groupe d'option*/
	int nbDays;

	public Reaffector(ArrayList<Student> scatterbrainStudent, ArrayList<ArrayList<Option>> options, int nbDays) {
		this.scatterbrainStudent = scatterbrainStudent;
		this.options = options;
		this.nbDays = nbDays;
	}

	/**
	 * Retourne l'option la moins peuplee parmis celles donnees en argument
	 * @param options la liste les options
	 * @return l'option la moins vide  
	 */
	private Option getMostEmpty(ArrayList<Option> options) {
		Option min = options.get(0);
		for (Option o : options) {
			min = (o.accepted.size() < min.accepted.size()) ? o : min;
		}
		return min;
	}

	/**
	 * Affecte a l'etudiant en argument l'option en argument
	 * @param opt l'option
	 * @param s l'etudiant
	 */
	private void affect(Option opt, Student s) {
		assert(!opt.isFull());
		opt.accepted.add(s);
		s.affected[opt.day] = opt;
	}
	
	/**
	 * Effectue l'affectation pour un groupe d'option
	 * @param notFull les options (pas pleines)
	 * @param unaffectedStudent les etudiants tete en l'air
	 */
	private void affectOneDay(ArrayList<Option> notFull, ArrayList<Student> unaffectedStudent) {
		while(!unaffectedStudent.isEmpty() && !notFull.isEmpty()) {
			System.out.println("affect");
			Student s  = unaffectedStudent.remove(0);
			Option min = getMostEmpty(notFull);
			affect(min,s);
			if(min.isFull()) {
				notFull.remove(min);
			}
		}
	}

	/**
	 * Affecte aux etudiants tete en l'air les options pour egaliser les effectifs de l'affectation.
	 */
	void affect() {
		for (int d = 0; d < nbDays; d++) {
			ArrayList<Option> notFull = new ArrayList<>();
			for (Option o : options.get(d)) {
				if (!o.isFull()) {
					notFull.add(o);
				}
			}
			affectOneDay(notFull, scatterbrainStudent);
		}
	}
}
