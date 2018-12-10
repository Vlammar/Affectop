package Calcul.Calcul.excel;

import java.util.List;

import Calcul.Calcul.bean.Student;

public interface ExcelAdapter {
	public List<Student> request(String path);
}
