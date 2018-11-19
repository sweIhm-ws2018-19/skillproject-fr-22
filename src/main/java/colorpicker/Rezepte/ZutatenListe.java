package main.java.colorpicker.Rezepte;

public enum ZutatenListe {
    Kartoffel(new Zutat("kartoffel")),
    Milch(new Zutat("milch"));



    public Zutat zutat;
    ZutatenListe(Zutat zutat){
        this.zutat = zutat;
    }

    public Zutat get() {
        return zutat;
    }
}
