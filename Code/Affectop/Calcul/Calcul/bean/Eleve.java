package Calcul.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Eleve extends MembreAmu{
	private Promotion promotion;
	private List<Option>  optionValide;
	private String numEtudiant;

	public Eleve(String nom, String prenom, String adresseMail, Promotion promotion, List<Option> optionValid�,
			String numEtudiant) {
		super(nom, prenom, adresseMail);
		this.promotion = promotion;
		if(optionValid�==null)
			optionValid�=new ArrayList<>();
		this.optionValid� = optionValid�;
		this.numEtudiant = numEtudiant;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<Option> getOptionValid�() {
		return optionValid�;
	}

	public void setOptionValid�(List<Option> optionValid�) {
		this.optionValid� = optionValid�;
	}

	public String getNumEtudiant() {
		return numEtudiant;
	}

	public void setNumEtudiant(String numEtudiant) {
		this.numEtudiant = numEtudiant;
	}
	public String toString() {
		return "["+numEtudiant+"] a valid�"+optionValid�.toString();
	}
}
