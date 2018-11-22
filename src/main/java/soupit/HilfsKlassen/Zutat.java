package main.java.soupit.HilfsKlassen;

public class Zutat {
    public final String zutat;
    final String grammatGeschlecht;

   public Zutat(String zutat,String grammatGeschlecht){
        this.zutat = zutat;
        this.grammatGeschlecht = grammatGeschlecht;
    }

    boolean equals(Zutat z){
       return (z.toString().equals(zutat) || (z.toString()).equals(zutat+"n"));
    }

    @Override
    public String toString() {
        return zutat;
    }
}
