package com.m1affectop.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Promotion {
	private int annee;
	private int niveau;
	private String filliere;
	private List<Eleve> eleves;
	
	public Promotion(int annee, int niveau, String filliere) {
		this.annee = annee;
		this.niveau = niveau;
		this.filliere = filliere;
		this.eleves = new ArrayList<>();;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public String getFilliere() {
		return filliere;
	}
	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}
	public List<Eleve> getEleves() {
		return eleves;
	}
	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}
	public void addEleve(Eleve e) {
		if(eleves==null||eleves.isEmpty()||!eleves.contains(e)) {
			eleves.add(e);
		}
	}
	public String toString() {
		String s=annee+" " +" "+ filliere+" "+niveau;
		for(Eleve e :eleves) {
			s+=e.toString();
		}
		return s;
	}
}