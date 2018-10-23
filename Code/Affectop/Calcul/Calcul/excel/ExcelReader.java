package Calcul.excel;

import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Calcul.bean.Eleve;



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
		// test
		Position r = getPosition("AZ265");
		System.out.println("colonne " + r.getColumn());
		System.out.println("ligne " + r.getLine());
		return null;
	}

	/**
     * Open a file with .xlsx and return a workbook
     *
     * */
    //TODO replace  the filestream by a file
    public static Workbook fileReader(String path) {
        Workbook wb = null;
        try (InputStream inp = new FileInputStream(path)) {
            wb = WorkbookFactory.create(inp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wb;
    }

	
	
	
	
	
	/**
     * Read a tabular wich start at the specified cell and read until it reach a null row, width specifies the width of the tabular
     * The format is
     * key value value ...
     * key value value ...
     * */
    public static TreeMap<String, ArrayList<Cell>> tabularReader(Workbook wb, Cell cell, int width) {

        TreeMap<String, ArrayList<Cell>> m = new TreeMap<>();
        Row row = cell.getRow();
        Sheet sheet = cell.getSheet();
        int tabular_row_start = cell.getRowIndex(), tabular_column_start = cell.getColumnIndex();
        Iterator iter = sheet.iterator();
        int r = 0;

        while (row.getCell(tabular_column_start) != null ||
               row.getCell(tabular_column_start).getStringCellValue() != null || !row.getCell(tabular_column_start)
                                                                                     .getStringCellValue()
                                                                                     .equals("")) {


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
            //lecture de la ligne
            for (int i = 1; i < width; i++) {
                line.add(row.getCell(tabular_column_start + i));
            }
            m.putIfAbsent(row.getCell(tabular_column_start).getStringCellValue(), line);
            r++;
        }
        return m;
    }


	
	
	
	
	// Utilities
	private void readTabular(String from,String to){
		Position start=getPosition(from);
		Position end=getPosition(to);
		
		//la ligne 1 contient les noms des colonnes des tableaux
		int l=start.getLine();
		
		
		for(;l<end.getLine();l++) {
			for(int c=start.getColumn();c<end.getColumn();c++) {
				
			}
		}
		
		
		
	}

	private Position getPosition(String s) {
		Position pos;
		int l,col=0;
		String[] splited = s.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");

		try {
			col = charToInt(splited[0]);
		} catch (cellNameException e) {
			System.out.println("Nom de la cellule invalide");
			e.printStackTrace();
		}
		l = Integer.parseInt(splited[1]) - 1;
		pos=new Position(l, col);
		return pos;
	}

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
