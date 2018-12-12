package baseIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BaseHandler {
	Statement st;
	
	/**
	 * Cree une connection avec la base MySQL et effectue la requete
	 * inspire par http://alvinalexander.com
	 * @param query la requete a effectuer
	 * @return le ResultSet contenant le resultat de la requete
	 */
	void initConnection() {
		
		try {
			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			
			String myUrl = "jdbc:mysql://51.75.120.5/affectop_test";
			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "baseaccess", "affectop2018");
			
			st = conn.createStatement();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void endConnection() {
		try {
		st.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
