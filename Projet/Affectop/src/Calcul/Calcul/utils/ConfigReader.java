package Calcul.Calcul.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfigReader {

	public ConfigReader() {
		File configFile = new File("/../configFile.txt");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			PrintWriter writer;
			try {
				writer = new PrintWriter(configFile);
				writer.println("base_path:");
				writer.println("port:");
				writer.println("identifiant:");
				writer.println("password_base:");
				writer.println("sntp:");
				writer.println("email_address:");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public String getBasePath() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("base_path:")) {
				s.replace("base_path:", "").trim();
				sc.close();
				return s;
			}
		}
		return "base_path";
	}
	
	public String getPort() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("port:")) {
				s.replace("port:", "").trim();
				sc.close();
				return s;
			}
		}
		return "port";
	}
	
	public String getIdentifiant() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("identifiant:")) {
				s.replace("identifiant:", "").trim();
				sc.close();
				return s;
			}
		}
		return "identifiant";
	}
	
	public String getPasswordBase() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("password_base:")) {
				s.replace("password_base:", "").trim();
				sc.close();
				return s;
			}
		}
		return "password_base";
	}
	
	public String getSNTP() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("sntp:")) {
				s.replace("sntp:", "").trim();
				sc.close();
				return s;
			}
		}
		return "sntp";
	}
	
	public String getEmailAddress() {
		Scanner sc = new Scanner("/../configFile.txt");
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.startsWith("email_address:")) {
				s.replace("email_address:", "").trim();
				sc.close();
				return s;
			}
		}
		return "email_address";
	}
}
