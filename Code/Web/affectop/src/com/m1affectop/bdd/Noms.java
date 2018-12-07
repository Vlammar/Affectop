package com.m1affectop.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.m1affectop.beans.BeanException;
import com.m1affectop.beans.Utilisateur;

public class Noms {
	private Connection connexion;
    
    public List<Utilisateur> recupererUtilisateurs() throws BeanException {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Statement statement = null;
        ResultSet resultat = null;
        
        loadDatabase();
        
        try {
            statement = connexion.createStatement();
            
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
        	String url = "jdbc:mysql://localhost:3306/javaee?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
            String user = "root";
            String motDePasse = "pass";

            connexion = DriverManager.getConnection( url, user, motDePasse );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        loadDatabase();
        
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}