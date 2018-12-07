package com.m1affectop.beans;

import java.util.Date;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Option {
	private String nom;

	private String description;
	private List<String> groupes;

	public Option(String nom, String description, List<String> groupes) {
		this.nom = nom;
		this.description = description;
		this.groupes = groupes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<String> groupes) {
		this.groupes = groupes;
	}
}