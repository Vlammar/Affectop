package com.m1affectop.beans;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class MembreAmu {
	private String nom;
	private String prenom;
	private String adresseMail;

	public MembreAmu(String nom, String prenom, String adresseMail) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

}