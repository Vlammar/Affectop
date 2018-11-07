package Calcul.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

import Calcul.base.BaseReader;
import Calcul.exceptions.UnexpectedFileException;
import Calcul.mail.MailManager;

public class TxtToMail {

	private MailManager mail;
	
	private File txtFile;
	
	public TxtToMail(String path) throws UnexpectedFileException{
		String[] s=path.split("\\\\");//For Windows 
		//String[] s=path.split("/");//For Linux 
		String fileName=s[s.length-1];
		s=fileName.split("\\.");
		//String state=s[0];
		
		s=fileName.split("[.]");
		
		if(!s[1].equals("txt")) {
			throw new UnexpectedFileException();
		}
		
		txtFile=new File(path);
		
		
		
		
		
		
		//TEST
		
		Map<String, ArrayList<String>> map=tagChecker("Content:contenu blabla <NOM><PRENOM><LISTE_AFFECTATION><DATE>qjscjhs><125azndjqsj>wxkkjwxkj jcnqslnc <>xx<qc><");
		for(Object key:map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
		/*for(String p:) {
		System.out.println(p+" "+p.length());	
		
		
		//Simply do a replace of <TAG>
		}*/
		
		
		
		
		//Read the text and make a mail object
		
		//Name of the file contains: teacher ID_mail name
		//with a mail name corresponding to a state 
		//For students:
		//AnnouncementOpenning 
		//WarningTimeLeft when time left is under a specified time limit and warn the people if they don't have submit their choices or reminds them what choices they have made
		//Results
		//For teacher:
		//AccountLink
		//EndOfAffectation
		//TeacherResultMail
		//For secretariat
		//SecretariatResultMail
		
		/*String subject="";
		String content="";
		String from="noreply.affectop@etu.univ-amu.fr";
		Address[] to;//mail to a specific student or to teacher or secretariat
		
		
		
		
		
		
		
		mail=new MailManager(subject,content,from,to);*/
		
	}
	private void readMail(String to,String token) {
		String content;
		BaseReader br=new BaseReader();
		
		Map<String, ArrayList<String>> map=tagChecker("Subject:test\nContent:contenu blabla <NOM><PRENOM><LISTE_AFFECTATION><DATE>qjscjhs><125azndjqsj>wxkkjwxkj jcnqslnc <>xx<qc><");
		ArrayList<String> list=map.get("Valid");
		for(String balise:list) {
			content.replace(balise,br.tagRequest(balise,token));
			
		}
		
		
		String subject;
		
		mailCreator("noreply.affectop@etu.univ-amu.fr", content, subject, to);
		
	}
	
	private String listeTag="NOM PRENOM DATE LISTE_AFFECTATION";
	//return all invalid tags
	
	
	
	/**Return all tag that don't belong to the listeTag, case sensitive
	 * 
	 * @param txt the string that need to be checked
	 * @return an ArrayList of String containing all tag that are not valid
	 * @author Valentin JABRE
	 * @version 1.2
	 * */
	private Map<String,ArrayList<String>> tagChecker(String txt) {
		HashMap<String,ArrayList<String>> res=new HashMap<>();
			
		Pattern patern=Pattern.compile("<["+listeTag+"]+>");
		Matcher matcher=patern.matcher(txt);
		
		ArrayList<String> result=new ArrayList<>();
		
		while(matcher.find()) {
			System.out.println("Valid "+matcher.group(0));
			result.add(matcher.group(0));
				
		}
		res.put("Valid", result);
		
		String s=txt.replaceAll("<["+listeTag+"]+>", "");
		
		
		patern=Pattern.compile("<[a-zA-Z0-9]*>");
		matcher = patern.matcher(s);
		result=new ArrayList<>();
		System.out.println("Detection balises invalides");
		while(matcher.find()) {
			System.out.println("invalide "+matcher.group(0));
			result.add(matcher.group(0));		
				
		}
			
		res.put("Invalid",result);
		return res;
	}
	
	
	private void mailCreator(String from,String content,String subject,Address[] to) {
		mail=new MailManager(subject, content, from, to);
		
	}
	
	/*private String extension(String fileName) {
		
		return fileName.split(".")[];
		
	}*/
}
