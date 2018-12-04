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
	/*	try {
			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/test";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "");

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM users";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Date dateCreated = rs.getDate("date_created");
				boolean isAdmin = rs.getBoolean("is_admin");
				int numPoints = rs.getInt("num_points");

				// print the results
				System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
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
