package Calcul.excel;

import java.util.List;
import java.util.Map.Entry;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import Calcul.bean.Eleve;
import Calcul.exceptions.UnexpectedFileException;
import Calcul.exceptions.cellNameException;

public class ExcelReader implements ExcelAdapter {
	public class Position {
		private int line;
		private int column;

		public Position(int l, int c) {
			this.line = l;
			this.column = c;
		}

		public int getLine() {
			return line;
		}

		public int getColumn() {
			return column;
		}
	}

	@Override
	public List<Eleve> request(String path) {

		Workbook wb = fileReader(path);
		Position p = getPosition("A1");
		TreeMap<String, ArrayList<Cell>> res = tabularReader(wb, wb.getSheetAt(0).getRow(p.line).getCell(p.column), 8);
		for (Entry<String, ArrayList<Cell>> e : res.entrySet()) {
			System.out.println(e.getKey());
			for (int i = 0; i < e.getValue().size(); i++) {
				// PB si nb
				if (e.getValue().get(i).getCellType() == CellType.STRING)
					System.out.println(e.getValue().get(i).getStringCellValue());
				if (e.getValue().get(i).getCellType() == CellType.NUMERIC)
					System.out.println(e.getValue().get(i).getNumericCellValue());
			}
		}

		return null;
	}

	/**
	 * Open a file with .xlsx and return a workbook
	 *
	 */
	// TODO replace the filestream by a file
	private Workbook fileReader(String path) {
		// TODO traiter le cas pour les .xls
		Workbook wb = null;

		try (InputStream inp = new FileInputStream(path)) {
			System.out.println(path);
			if(path.endsWith("xlsx")){
				wb = XSSFWorkbookFactory.create(inp);;
			}else if(path.endsWith("xls")){
				wb = HSSFWorkbookFactory.create(inp);
			}else{
				throw new UnexpectedFileException();
			}
			
			wb = XSSFWorkbookFactory.create(inp);
			System.out.println("wb created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * Read a tabular which start at the specified cell and read until it reach a
	 * null row, width specifies the width of the tabular The format is key value
	 * value ... key value value ...
	 * 
	 * @version 2.0
	 */
	public static TreeMap<String, ArrayList<Cell>> tabularReader(Workbook wb, Cell cell, int width) {

		TreeMap<String, ArrayList<Cell>> m = new TreeMap<>();
		Row row = cell.getRow();
		Sheet sheet = cell.getSheet();
		int tabular_row_start = cell.getRowIndex(), tabular_column_start = cell.getColumnIndex();
		
		int r = 0;

		while (row.getCell(tabular_column_start) != null
				|| row.getCell(tabular_column_start).getStringCellValue() != null
				|| !row.getCell(tabular_column_start).getStringCellValue().equals("")) {

			row = sheet.getRow(tabular_row_start + r);

			if (row == null) {
				System.out.println("row null " + r);
				break;
			}
			if (row.getZeroHeight()) {
				r++;
				continue;
			}
			ArrayList<Cell> line = new ArrayList<>();
			// lecture de la ligne
			for (int i = 1; i < width; i++) {
				line.add(row.getCell(tabular_column_start + i));
			}
			m.putIfAbsent(row.getCell(tabular_column_start).getStringCellValue(), line);
			r++;
		}
		return m;
	}

	/**
	 * Read a tabular which start at the specified cell and read until it reach a
	 * null row, width specifies the width of the tabular The format is key value
	 * value ... key value value ...
	 */
	public static TreeMap<String, ArrayList<Cell>> tabularReader2(Workbook wb, Cell cell, int width) {

		TreeMap<String, ArrayList<Cell>> m = new TreeMap<>();
		Row row = cell.getRow();
		Sheet sheet = cell.getSheet();
		int tabular_row_start = cell.getRowIndex(), tabular_column_start = cell.getColumnIndex();
		int r = 0;

		while (row.getCell(tabular_column_start) != null
				|| row.getCell(tabular_column_start).getStringCellValue() != null
				|| !row.getCell(tabular_column_start).getStringCellValue().equals("")) {

			row = sheet.getRow(tabular_row_start + r);

			if (row == null) {
				System.out.println("row null " + r);
				break;
			}
			if (row.getZeroHeight()) {
				r++;
				continue;
			}
			ArrayList<Cell> line = new ArrayList<>();
			// lecture de la ligne
			for (int i = 1; i < width; i++) {
				line.add(row.getCell(tabular_column_start + i));
			}
			m.putIfAbsent(row.getCell(tabular_column_start).getStringCellValue(), line);
			r++;
		}
		return m;
	}

	// Utilities
	private void readTabular(String from, String to) {
		Position start = getPosition(from);
		Position end = getPosition(to);

		// la ligne 1 contient les noms des colonnes des tableaux
		int l = start.getLine();

		for (; l < end.getLine(); l++) {
			for (int c = start.getColumn(); c < end.getColumn(); c++) {

			}
		}

	}

	private Position getPosition(String s) {
		Position pos;
		int l, col = 0;
		String[] splited = s.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");

		try {
			col = charToInt(splited[0]);
		} catch (cellNameException e) {
			System.out.println("Nom de la cellule invalide");
			e.printStackTrace();
		}
		l = Integer.parseInt(splited[1]) - 1;
		pos = new Position(l, col);
		return pos;
	}

	/**
	 * Convert an excel row name into an integer AB=27
	 * 
	 * @param string
	 *            the string you want to convert
	 * @return the converted string
	 * @throws cellNameException if the given name is not of the type [A-Z]+
	 * @version 1.1
	 * @author Valentin JABRE
	 */
	private int charToInt(String string) throws cellNameException {
		int res = 0;
		for (int i = string.length() - 1; i >= 0; i--) {
			char c = string.charAt(i);
			int v = ((int) c) - ((int) 'A');
			System.out.println("v=" + v);
			if (v >= 0 || v < 26) {
				res += Math.pow(26, string.length() - i - 1) * (v + 1);
			} else {
				throw new cellNameException();
			}

		}
		return res - 1;
	}

}
