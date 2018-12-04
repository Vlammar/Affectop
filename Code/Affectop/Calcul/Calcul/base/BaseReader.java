package Calcul.base;

import java.sql.*;

/**
 * This base reader can only be used with a mysql database
 * 
 */
public class BaseReader implements BaseAdapter {

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
			Connection conn = DriverManager.getConnection(myUrl, "baseaccess", "affectop2018");
			System.out.println(conn);
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM Students";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			System.out.println("Affichage :");
			while (rs.next()) {
				String token = rs.getString("token");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String numetu = rs.getString("numEtudiant");
				String mail = rs.getString("mail");
				String step = rs.getString("step");
				String year = rs.getString("year");
				// print the results
				System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Return the database content associated with the tag
	 * @param tag 
	 * @param token primary key used for accessing database specified value for a given student
	 * */
	public String tagRequest(String tag, String token) {
		String tagcontent=tag.substring(1, tag.length()-1);
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

	public String nameRequest(String token) {
		return "nameRequest";
	}

	public String firstNameRequest(String token) {

		return "firstNameRequest";
	}

	private String affectListRequest(String token) {

		return "affectListRequest";
	}

	public String dateRequest(String token) {

		return "dateRequest";
	}
}
