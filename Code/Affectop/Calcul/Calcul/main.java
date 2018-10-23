package Calcul;

import java.util.ArrayList;

import Calcul.bean.Eleve;
import Calcul.bean.Promotion;
import Calcul.excel.ExcelReader;

public class main {

	public static void main(String[] args) {
		String[] prenoms= {"test","toto","mathieu","val","pdf"};
		String[] noms= {"1","2","3","4","5"};
		String[] mails= {"test@mail.com","toto@mail.com","mathieu@mail.com","val@mail.com","pdf@mail.com"};

		ArrayList<Eleve> eleves=new ArrayList<>();
		Promotion promo=new Promotion(2018, 1, "Master IAAA");
		for(int i=0;i<noms.length;i++) {
			System.out.println(i);
			Eleve e=new Eleve(noms[i], prenoms[i], mails[i], promo, null, "testschjqshc");
			promo.addEleve(e);
			
		}
		System.out.println(promo.toString());
		ExcelReader ex=new ExcelReader();
		ex.request("test");
		
		
	}

}
