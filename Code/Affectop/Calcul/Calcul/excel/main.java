package Calcul.excel;

import java.io.File;
import java.util.ArrayList;

import Calcul.bean.Eleve;
import Calcul.bean.Promotion;
import Calcul.excel.ExcelReader;

public class main {

	public static void main(String[] args) {

		ExcelReader ex=new ExcelReader();
		ex.request("Datas"+File.separator+"2017 11 23 IA Saint-Charles.xlsx");
		
		
	}

}
