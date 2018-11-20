package main.java.colorpicker.HilfsKlassen;

public enum EinheitenListe {

    GRAMM("gramm","n"),
    KILO("kilo","n"),
    MILLILITER("milliliter","m"),
    LITER("liter","m"),
    NULL("",""),
    ESSLÖFFEL ("esslöffel","m"),
    STÄNGEL ("stängel","m"),
    TEELÖFFEL ("teelöffel","m"),
    PRISE ("prise","f"),
    PRISEN ("prisen","f")
    ;

    public Einheit einheit;
    EinheitenListe(String einheit, String grammatGeschlecht ){
     this.einheit  = new Einheit(einheit,grammatGeschlecht);
    }

    public Einheit get(){
        return einheit;
    }


}
