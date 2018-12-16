package baseIO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import calcul.Affectop;
import calcul.Option;
import calcul.Student;

/**
 * This base reader can only be used with a mysql database
 * 
 */
public class BaseReader extends BaseHandler{

	Map<Integer,Option> options = new HashMap<Integer, Option>();
	Map<Integer,Student> students = new HashMap<Integer, Student>();
	
	ResultSet getResultOfQuery(String query) {
		try {
			return st.executeQuery(query);
		}
		catch(Exception e){
			System.out.println("EXEC ERROR");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Créé et retourne les options présentes dans la BD MySQL (classées par jours)
	 * @param year l'annee de l'affectation
	 * @return la liste de liste d'options
	 */
	public ArrayList<ArrayList<Option>> getOptionsPerDays(int year){
		String query = "SELECT * FROM Options where year = "+year+" ;";
		int nbDays = getNbDays(year);
		
		ResultSet rs = getResultOfQuery(query);
		
		ArrayList<ArrayList<Option>> result = new ArrayList<>();
		
		for(int d = 0 ; d < nbDays ; d++) {
			result.add(new ArrayList<>());
		}
		
		try {
			while (rs.next()) {
				String intitule = rs.getString("intitule");
				
				int size = rs.getInt("size");
				int optionGroup = rs.getInt("optionGroup");
				int id = rs.getInt("id");
				
				Option o = new Option(size, intitule, optionGroup,id);
				options.put(id,o);
				result.get(optionGroup-1).add(o);
			}
			return result;
		}
		catch(Exception e) {
			System.out.println("ERREUR OPTIONS");
			e.printStackTrace();
			return result;
		}
	}
	
	
	/**
	 * 
	 * Créé, remplit les preferences et retourne les étudiants présents dans la BD MySQL 
	 * 
	 * @param year l'annee de l'affectation
	 * @return la liste d'etudiants
	 */
	public ArrayList<Student> getStudents(int year){
		String query = "SELECT * FROM Students where year = "+year+" ;";
		int nbDays = getNbDays(year);
		
		
		ResultSet rs = getResultOfQuery(query);
		
		
		ArrayList<Student> result = new ArrayList<>();
		
		// iterate through the java resultset
		try {
			while (rs.next()) {
				String mail = rs.getString("mail");
				Integer numEtu = rs.getInt("numEtudiant");
				
				Student s =new Student(mail, null, nbDays,numEtu); 
				students.put(numEtu,s);
				result.add(s);
				
			}
			
			
			for(Student s : students.values()){
				ArrayList<LinkedList<Option>> preferences = getStudentPreference(nbDays, s.numEtu, year);
				s.preferences = preferences;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * Créé et retourne les options présentes dans la BD MySQL 
	 * @param year l'annee de l'affectation
	 * @return la liste de liste d'options
	 */
	
	public ArrayList<Option> getOptions(int year){
		String query = "SELECT * FROM Options where year = "+year+" ;";

		ResultSet rs = getResultOfQuery(query);		
		ArrayList<Option> result = new ArrayList<>();
		
		options.clear();
		
		try {
			while (rs.next()) {
				String intitule = rs.getString("intitule");
				
				int size = rs.getInt("size");
				int optionGroup = rs.getInt("optionGroup");
				int id = rs.getInt("id");
				
				Option o = new Option(size, intitule, optionGroup,id);
				options.put(id,o);
				result.add(o);
				//System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	/**
	 * Créé et retourne les preferences des etudiants présents dans la BD MySQL (classées par jours)
	 * @param nbDays le nombre de groupes d'option
	 * @param studentID le numero etudiant de l'etudiant
	 * @param year l'annee de l'affectation
	 * @return la liste de liste d'options
	 */
	public ArrayList<LinkedList<Option>> getStudentPreference(int nbDays, int studentID, int year){
			ArrayList<LinkedList<Option>> result = new ArrayList<>();
			String query = 
				"SELECT * FROM Preferences where numEtudiant = '"+studentID+"' ORDER BY choice;" ;
			ResultSet rs = getResultOfQuery(query);
			ArrayList<Option> optionsAllDaysSortedByPreferences = new ArrayList<>();
			try {
				while (rs.next()) {
					optionsAllDaysSortedByPreferences.add(options.get(rs.getInt("optionId")));
				}
				for(int d = 1 ; d <= nbDays ; d ++) {
					LinkedList<Option> todayPreference = new LinkedList<>() ;
					for (Option opt : optionsAllDaysSortedByPreferences) {
						if(opt != null && opt.getDay() == d)
							todayPreference.addLast(opt);
					}
					result.add(todayPreference);
				}				
				
				return result;
			}
			catch(Exception e) {
				e.printStackTrace();
				return result;
			}
	}	
	
	/**
	 * Retourne le nombre de groupe d'option  dans la BD MySQL
	 * @param year l'annee de l'affectation
	 * @return le nombre de groupes d'option
	 */
	public int getNbDays(int year) {
		String query = 
				"select MAX(optionGroup) from Options where year ="+year+";" ;
		ResultSet rs = getResultOfQuery(query);
		
		try {
			if(rs.next())
				return  rs.getInt("MAX(optionGroup)");
		}
		catch(Exception e) {
			System.out.println("ERREUR NBDAYS");
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * Créé et retourne le dictionnaire des incompatibilites entre options présent dans la BD MySQL
	 * (pour le moment : juste le dictionnaire d'options avec le meme intitule)
	 * @return le dictionnaire des incompatibilites entre options
	 */
	public Map<Option,ArrayList<Option>> getIncompatibilities(){
		Map<Option,ArrayList<Option>> incompatibilities = new HashMap<Option, ArrayList<Option>>();
		
		for(Option opt1 : options.values()) {
			incompatibilities.put(opt1,new ArrayList<>());
			for(Option opt2 : options.values()) {
			if(opt1.getIntitule() == opt2.getIntitule())
				incompatibilities.get(opt1).add(opt2);
			}
		}
		return incompatibilities;
	}

	/**
	 * Créé et retourne le dictionnaire qui a pour cle les etudiants et pour valeur  la list des options redoublees entre options présent dans la BD MySQL
	 * (pour le moment : juste meme intitule)
	 * @return le dictionnaire <K:etudiant,V:liste de ses redoublements>
	 */
	public Map<Student, ArrayList<Option>> getRepeater(int year) {
		String query = "SELECT * FROM Repeaters WHERE optionId IN ("
				+ "SELECT id FROM Options WHERE year ="+year+") ;";
		ResultSet rs = getResultOfQuery(query);
		
		Map<Student, ArrayList<Option>> repeaters = new HashMap<Student, ArrayList<Option>>();

		// iterate through the java resultset
		try {
			while (rs.next()) {
				
				int opt = rs.getInt("optionId");
				int numEtu = rs.getInt("numEtudiant");

				if(!repeaters.containsKey(students.get(numEtu)))
					repeaters.put(students.get(numEtu), new ArrayList<>());

				repeaters.get(students.get(numEtu)).add(options.get(opt));
				//System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERREUR");
			return null;
		}
		return repeaters;
	}
	
	/** Retourne si le token passe en argument est celui du professeur. 
	 * @param token le token
	 * @param year	l'annee choisie
	 * @return un booleen vrai si le token est celui du professeur et faux sinon.
	 */
	boolean isTeacher(String token, int year) {
		String query =  "SELECT COUNT(*) from Teachers WHERE token = '"+ token +"' AND year ="+year;
		ResultSet rs = getResultOfQuery(query);
		try {
			if(rs.next()) {
				if(rs.getInt("COUNT(*)")>0)
					return true;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	

	public ArrayList<Student> getScatterBrainStudent(int year){
		String query = "SELECT * FROM Studdents WHERE numEtudiant in (SELECT DISTINCT numEtu FROM Preferences)";
		ArrayList<Student> scatterBrainStuddent = new ArrayList<>();
		ResultSet rs = getResultOfQuery(query);
		try {
			while (rs.next()) {
				int numEtu = rs.getInt("numEtudiant");
				Student s = students.get(numEtu);
				if(s!=null)
					scatterBrainStuddent.add(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return scatterBrainStuddent;
	}
	
	public static void main(String[] args) {
		BaseReader br = new BaseReader();
		br.initConnection();
		
		int nbDays = br.getNbDays(2017);
		
		ArrayList<ArrayList<Option>> options = br.getOptionsPerDays(2017);
		ArrayList<Student> students = br.getStudents(2017);
		Map<Student, ArrayList<Option>> repeaters = br.getRepeater(2017);
		Map<Option,ArrayList<Option>> incompatibilities = br.getIncompatibilities();
		
		
		ArrayList<Student> scatterBrainStudents = new ArrayList<>();
		for(Student s : students) {
			if(s.preferences.get(0).isEmpty())
				scatterBrainStudents.add(s);
		}
		
		students.removeAll(scatterBrainStudents);
		
		Affectop aff = new Affectop(nbDays, students, options, repeaters, scatterBrainStudents, incompatibilities);
		//System.out.println(aff.affectTop(nbDays,1));
		
		
		
		//System.out.println(new calcul.Result(students).results);
		System.out.println(br.isTeacher("_f_s_-é",2017));
		System.out.println(br.isTeacher("_f_s_-é",2016));
		System.out.println(br.isTeacher("blah",2017));
		
		try {
			if(br != null && br.st != null)
				br.st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}