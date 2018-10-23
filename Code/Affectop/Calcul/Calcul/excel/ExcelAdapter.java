package Calcul.excel;

import java.util.List;

import Calcul.bean.Eleve;

public interface ExcelAdapter {
	public List<Eleve> request(String path);
}
