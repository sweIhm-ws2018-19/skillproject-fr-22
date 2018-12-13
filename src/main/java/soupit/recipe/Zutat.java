package soupit.recipe;

public class Zutat {
    private final String zutat;
    private final String grammatGeschlecht;

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

    public String getGrammatGeschlecht() {
        return grammatGeschlecht;
    }
}
