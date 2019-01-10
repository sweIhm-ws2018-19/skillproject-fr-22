package soupit.recipe;

import soupit.SessionAttributes;

public class Zutat {
    private final String zutat;
    private final String grammatGeschlecht;
    private final String plural;

    public Zutat(String zutat,String grammatGeschlecht, String plural){
        this.zutat = zutat;
        this.grammatGeschlecht = grammatGeschlecht;
        this.plural = plural;
    }

    boolean equals(Zutat z){
//        for(String s:(SessionAttributes.synonyme.get(z.toString()))){
//            if (s!= null && s.equalsIgnoreCase(zutat)) return true;
//        }
        return (z.toString().equals(zutat) || (z.toString()).equals(zutat+"n") );
    }

    @Override
    public String toString() {
        return zutat;
    }

    public String getGrammatGeschlecht() {
        return grammatGeschlecht;
    }

    public String getPlural(){
        return plural;
    }
}
