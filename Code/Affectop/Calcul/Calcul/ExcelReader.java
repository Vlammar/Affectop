package Calcul;

import java.util.List;

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

	// Utilities
	private void readTabular(String from,String to){
		Position start=getPosition(from);
		Position end=getPosition(to);
		for(int l=start.getLine();l<end.getLine();l++) {
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
