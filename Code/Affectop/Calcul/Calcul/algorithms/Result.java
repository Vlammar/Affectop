package calcul;

import java.util.ArrayList;

public class Result implements Comparable<Result>{
	public class Affectation {
		Student s;
		ArrayList<Option> options;
		public Affectation(Student s, ArrayList<Option> options) {
			this.s =s;
			this.options = options;
		}
	}
	
	ArrayList<Affectation> results;
	public Result(ArrayList<Student> students) {
		results = new ArrayList<>();
		for(Student s : students) {
			ArrayList<Option> options = new ArrayList<>();
			options.addAll(s.affected);
			results.add(new Affectation(s, options));
		}
	}
	
	private int preference(Student s, Option opt) {
		return s.preferences.get(opt.day).size() - s.preferences.get(opt.day).indexOf(opt);
	}
	
	int satisfaction() {
		int satisfaction = 0;
		for(Affectation a : results) {
			for(Option opt  : a.s.affected)
				satisfaction += a.s.preferences.get(opt.day).size() - a.s.preferences.get(opt.day).indexOf(opt);
			satisfaction -= (a.options.size() - a.s.preferences.size()) * 10;
		}
		return satisfaction;
	}
	@Override
	public int compareTo(Result r) {
		return this.satisfaction()-r.satisfaction();
	}
}