
public class Adresse {
	private String strasse;
	private int hausnummer;
	private String plz;
	private String ort;
	
}


public class Geschäftskunde extends Kunde {
	private String firmenname;
	private Adresse domizilAdresse;
}


public class Konto {
	private String bezeichnung;
	private Kunde zeichnungsberechtigter;
	
	public double saldo() {
		return Geldbetrag;
	}
	public void einzahlen(double eingehenderBetrag, double Geldbetrag) {
		
	}
}


public abstract class Kunde {
	private Konto konto;

}


public class Privatkunde extends Kunde {
	private String vorname;
	private String nachname;
	private Adresse postAdresse;
}
