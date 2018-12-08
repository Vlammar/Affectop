package Calcul.base;

import java.sql.*;
import java.util.ArrayList;

import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;

/**
 * This base reader can only be used with a mysql database
 * 
 */
public class BaseReader implements BaseAdapter {
	private Connection conn;

	public BaseReader() {

		/**
		 * A Java MySQL SELECT statement example. Demonstrates the use of a SQL SELECT
		 * statement against a MySQL database, called from a Java program.
		 * 
		 * Created by Alvin Alexander, http://alvinalexander.com
		 */
		try {
			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";

			String myUrl = "jdbc:mysql://51.75.120.5/affectop_test";
			System.out.println(myUrl);
			Class.forName(myDriver);
			System.out.println("test");
			conn = DriverManager.getConnection(myUrl, "basaccess", "affectop2018");

			System.out.println("connexion etablie");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Return the database content associated with the tag
	 * 
	 * @param tag
	 * @param token
	 *            primary key used for accessing database specified value for a
	 *            given student
	 */
	public String tagRequest(String tag, String token) {
		String tagcontent = tag.substring(1, tag.length() - 1);
		System.out.println(tagcontent);
		if (tagcontent.equalsIgnoreCase("nom")) {
			return nameRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("prenom")) {
			return firstNameRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("LISTE_AFFECTATION")) {
			return affectListRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("date")) {
			return dateRequest(token);
		}
		return "/!\\unknown tag/!\\ ";
	}

	private String studentsQueryBuilder(String col, String token) {

		return "SELECT " + col + " FROM Students WHERE token=\"" + token + "\";";
	}

	private String studentsQueryRequest(String col, String token) {

		String query = studentsQueryBuilder(col, token);
		System.out.println(query);
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString(col);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	private String teachersQueryBuilder(String col, String token) {
		return "SELECT " + col + " FROM Teachers WHERE token=\"" + token + "\";";

	}

	private String teachersQueryRequest(String col, String token) {

		String query = teachersQueryBuilder(col, token);
		System.out.println(query);
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString(col);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	public String nameRequest(String token) {
		return studentsQueryRequest("lastname", token);

	}

	public String firstNameRequest(String token) {

		return studentsQueryRequest("firstname", token);
	}

	public int tokenIdentification(String token) {

		String resQueryStudent = studentsQueryRequest("token", token);
		if (resQueryStudent != null && !resQueryStudent.equals(""))
			return 1;

		String resQueryTeacher = teachersQueryRequest("token", token);

		if (resQueryTeacher != null && !resQueryTeacher.equals(""))
			return 2;
		return 0;
	}

	//TODO ne marche pas car on va confondre les années et les sessions, il faut une jointure entre le prof et les options puis avec les prefs et leseleves
	public ArrayList<String> studentsValidateListRequest() {
		ArrayList<String> students = new ArrayList<>();

		String query = "SELECT token FROM Students WHERE numEtudiant in (SELECT DISTINCT numEtudiant FROM  Preferences );";
	//	String query = "SELECT * FROM Students INNER JOIN Teachers ON table1.id = table2.fk_id ;";
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString("token");
				students.add(res);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return students;

	}

	private String affectListRequest(String token) {

		return "affectListRequest";
	}

	public String dateRequest(String token) {

		return "dateRequest";
	}
}
