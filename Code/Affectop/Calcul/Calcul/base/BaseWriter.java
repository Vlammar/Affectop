package baseIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import calcul.Option;
import calcul.Result;
import calcul.Result.Affectation;
import calcul.Student;

public class BaseWriter extends BaseHandler{	
	
	/**
	 * Ecrit le resultat de l'algorithme d'affectation dans la base de donnee (ici BD MySQL)
	 * 
	 * @param year l'annee
	 * @param r le resultat de l'algorithme d'affectation 
	 */
	public void writeResults(int year,Result r ){
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Results (optionId, numEtudiant)\n"); 
		try {
			for (Affectation aff : r.results) {
				for(Option opt : aff.options)
					query.append("(" +opt.id+" "+aff.s.numEtu+"),\n"); 
			}
			query.replace (query.length()-2,query.length()-1,";");
			System.out.println(query.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * Ajoute un etudiant a la base de donnee (ici BD MySQL)
	 * 
	 * @param year l'annee courante
	 * @param lastName le prenom de l'etudiant
	 * @param firstName le nom de l'etudiant
	 * @param numEtu le numero etudiant
	 * @param mail le mail de l'etudiant
	 * @param token le token de l'etudiant
	 * @param step l'etape
	 */
	public void writeStudent(int year,
							String lastName,
							String firstName,
							String numEtu,
							String mail,
							String token,
							String step) {
		
		String query = "INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)"+
							"VALUES("+year+",'"+lastName+"','"+firstName+"','"+numEtu+"','"+mail+"','"+token+"','"+step+"')";
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Ecrit dans la base de donee les preferences de l'etudiant
	 * @param numEtudiant le numero de l'etudiant
	 * @param preferences les preferences de l'etudiant
	 */
	public void writePreference(int numEtudiant, ArrayList<Option> preferences) {
		int index = 1;
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Preferences (choice,optionId,numEtudiant) \nVALUES\n");
		for(Option opt : preferences) {
			query.append("("+index+","+opt.id+","+numEtudiant+"),");
			index ++;
		}
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Ecrit dans la base de donee les options
	 * @param options les options 
	 * @param year l'annee
	 */
	public void writeOptions(ArrayList<Option> options, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Options (intitule,description,size,optionGroup,year) \nVALUES\n");
		for(Option opt : options) {
			query.append("('"+opt.intitule+"','"+opt.description+"',"+opt.size+","+opt.day+","+year+"),");
		}
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Retire des etudiants de la base l'etudiant dont le numero est passe en argument
	 * @param numEtudiant le numEtudiant
	 */
	public void deleteOption(int id) {
		String query = "DELETE FROM Options WHERE id ="+id+";";
				
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Retire des etudiants de la base l'etudiant dont le numero est passe en argument
	 * @param numEtudiant le numEtudiant
	 */
	public void deleteStudent(String numEtudiant) {
		String query = "DELETE FROM Students WHERE numEtudiant ="+numEtudiant+";";
				
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit dans la BD les redoublants
	 * @param repeaters
	 * @param year
	 */
	public void writeRepeaters(Map<Student,ArrayList<Option>> repeaters, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Repeaters (numEtudiant,optionId,year)\nVALUES\n");
		
		for(Student s : repeaters.keySet()) {
			for(Option opt : repeaters.get(s))
				query.append("("+s.numEtu+","+opt.id+","+year+"),");
		}
		query.replace(query.length()-1, query.length(), ";");
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BaseWriter b = new BaseWriter();
		b.initConnection();;
		
		//STUDENTS
		//b.writeStudent(1,"Jesus","Christ","1","JEEZ_eden.com","aaaaaaaaaaaaaaaa","1");
		//b.deleteStudent("1");
		
		//PREFERENCES
		/*ArrayList<LinkedList<Option>> prefs = new ArrayList<>();
		LinkedList<Option> opt = new LinkedList<Option>();
		opt.add(new Option(1, "faire ses lacets", 1, 666));
		opt.add(new Option(2, "karate", 2, 333));
		prefs.add(opt);*/
		
		//b.writePreference(1, new ArrayList<>(opt));
		
		//OPTIONS
		/*ArrayList<Option> options = new ArrayList<>();
		
		Option opt1 = new Option(10000, "blablabla", "RSA", 1, 99);
		Option opt2 = new Option(10001, "spé chomage ", "arts du cirque ", 2, 13);
		options.add(opt1);
		options.add(opt2);*/
		//b.writeOptions(options, 2017);
		
		//REPEATEARS
		Map<Student, ArrayList<Option>> repeat = new HashMap<>();
		
		//b.writeStudent(1,"Jesus","Christ","1","JEEZ_eden.com","aaaaaaaaaaaaaaaa","1");
		//b.writeStudent(1,"Monkey","D Luffy","2","gumm_gumm.com","wooon","2");
		
		BaseReader br = new BaseReader();
		ArrayList<Option> ops = br.getOptions(2017);
		ArrayList<Student> sts = br.getStudents(2017);
		
		
		ArrayList<Option> jesusRepeater = new ArrayList<>();
		jesusRepeater.add(ops.get(0));
		repeat.put(sts.get(0), jesusRepeater);
		
		b.writeRepeaters(repeat,1997);
		b.endConnection();
	}
}
