package Calcul.algorithms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Main {
	static int min(int a, int b) {
		return a <= b ? a : b;
	}

	static int preference(Student s, Option o) {
		return s.preference.size() - s.preference.indexOf(o);
	}

	static boolean areWillingToExchange(Student s1,Option opt1, Student s2,Option opt2) {
		
		for(Option o : s1.affected)
			if(opt2.day == o.day || opt2.intitule == o.intitule) {
				return false;
			}
		
		for(Option o : s2.affected)
			if(opt1.day == o.day || opt1.intitule == o.intitule) {
				return false;
			}
		
		if ((preference(s1, opt2) > preference(s1, opt1)
				&& (preference(s2, opt1) >= preference(s2, opt2)))) {
			return true;
		}
		return false;
	}

	static boolean isStable(ArrayList<Student> students) {
		for (Student s1 : students)
			for (Student s2 : students)
				for(Option opt1 : s1.affected)
					for(Option opt2 : s2.affected)
						if (s1 != s2 && areWillingToExchange(s1,opt1, s2,opt2)) {
							return false;
				}
		return true;
	}
	
	static public void randomizeOption(ArrayList<Option> options, int n, int minSize, int maxSize,int day) {
		Random rand = new Random();
		for (int opt = 0; opt < n; opt++) {
			int size = minSize + (Math.abs(rand.nextInt()) % maxSize - minSize + 1);
			options.add(new Option(size, "opt" + opt,day));
		}
	}

	static public void randomizeStudents(ArrayList<Student> students, int n, ArrayList<Option> options) {
		for (int i = 0; i < n; i++) {
			LinkedList<Option> studentOps = new LinkedList<Option>();
			studentOps.addAll(options);
			Collections.shuffle(studentOps);

			Student current = new Student("mail " + i, studentOps);
			students.add(current);
		}
	}

	
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Option> options = new ArrayList<>();
		
		int nbDays = 3;

		
		Option opt11 = new Option(1,"o11",1);		options.add(opt11);
		Option opt21 = new Option(1,"o21",1);		options.add(opt21);
		Option opt31 = new Option(1,"o31",1);		options.add(opt31);
		
		Option opt12 = new Option(1,"o12",2);		options.add(opt12);
		Option opt22 = new Option(1,"o22",2);		options.add(opt22);
		Option opt32 = new Option(1,"o32",2);		options.add(opt32);		

		Option opt13 = new Option(1,"o13",3);		options.add(opt13);
		Option opt23 = new Option(1,"o23",3);		options.add(opt23);
		Option opt33 = new Option(1,"o33",3);		options.add(opt33);		

		LinkedList<Option> liste1 = new LinkedList<Option>();
		liste1.addLast(opt11);liste1.addLast(opt12);liste1.addLast(opt13);
		liste1.addLast(opt21);liste1.addLast(opt22);liste1.addLast(opt23);
		liste1.addLast(opt31);liste1.addLast(opt32);liste1.addLast(opt33);

		
		students.add(new Student("s1", liste1));
		students.add(new Student("s2", liste1));
		students.add(new Student("s3", liste1));
		
		
		Affectop currentAffect = new Affectop(students, options,3);
		currentAffect.affectStable();
		
		for(Student s : students)
			System.out.println(s.affected);


	}
}
