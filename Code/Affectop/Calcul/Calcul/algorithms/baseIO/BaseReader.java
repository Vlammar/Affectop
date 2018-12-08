package baseIO;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;*/


public class BaseReader {
/*	private Connection connexion;
    
    public void recupererUtilisateurs(){
        loadDatabase();
      
        try {
            statement = connexion.createStatement();
            
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM Students;");

            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) { 
        	Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(e.toString());
            utilisateur.setPrenom("exp");
            
            utilisateurs.add(utilisateur);
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        
        return utilisateurs;
    }
    
    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.mysql-connector");
        } catch (ClassNotFoundException e) {
        }

        try {
        	
        	String url = "jdbc:mysql://51.75.120.5:3306/affectop_test";
            String user = "root";
            String motDePasse = "pass";

            connexion = DriverManager.getConnection( url, user, motDePasse );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		BaseReader b = new BaseReader();
		b.loadDatabase();
	}*/
}