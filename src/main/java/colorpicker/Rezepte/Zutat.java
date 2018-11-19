package main.java.colorpicker.Rezepte;

public class Zutat {
    public final String zutat;

   public Zutat(String zutat){
        this.zutat = zutat;
    }

    boolean equals(Zutat z){
       return (z.toString().equals(zutat) || (z.toString()+"n").equals(zutat));
    }

    @Override
    public String toString() {
        return zutat;
    }
}
