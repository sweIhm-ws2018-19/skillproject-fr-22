package main.java.colorpicker.HilfsKlassen;


public enum ZutatenListe {
    KARTOFFEL("kartoffel","f"),
    MILCH("milch","f"),
    ZWIEBEL("zwiebel","f"),
    GEMÜSEBRÜHE("gemüsebrühe","f"),
    ÖL("öl","n"),
    SAHNE("sahne","f"),
    PETERSILIE("petersilie","f"),
    SALZ("salz","n"),
    PFEFFER("pfeffer","m"),
    MUSKAT("muskat","m"),
    SCHINKENWÜRFEL("schinkenwürfel","f"),
    SPECKWÜRFEL("speckwürfel","f"),
    ;



    public Zutat zutat;
    ZutatenListe(String zutat,String grammatGeschlecht){
        this.zutat = new Zutat(zutat,grammatGeschlecht);

    }

    public Zutat get() {
        return zutat;
    }
}
