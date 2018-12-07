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
	private List<Date> dates;

	public Option(String nom, String description, List<Date> dates) {
		this.nom = nom;
		this.description = description;
		this.dates = dates;
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

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	public String toString() {
		String s= nom+": "+description+" le ";
		for(int i=0;i<dates.size();i++) {
			Date d=dates.get(i);
			s+=d.toString();
			if(i<dates.size()-1) {
				s+=" et le ";
			}
			else {
				s+=".";
			}
			
		}
		return s;
	}
}