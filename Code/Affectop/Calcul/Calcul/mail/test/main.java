package Calcul.mail.test;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class main {

	public static void main(String[] args) {

		Address[] to = new Address[4];
		try {
			to[0] = new InternetAddress("valentin.jabre@gmail.com");

			to[1] = new InternetAddress("valentin.jabre@etu.univ-amu.fr");

			to[2] = new InternetAddress("valentin.jabre@gmil.com");
			to[3] = new InternetAddress("valentinjkcjsdhcdsc@gmail.com");
		} catch (AddressException e) {
		
			e.printStackTrace();
		}
		mail m = new mail("test", "contenu", "noreply.Affectop@etu.univ-amu.fr", to);
		m.sendMail();

	}
}
