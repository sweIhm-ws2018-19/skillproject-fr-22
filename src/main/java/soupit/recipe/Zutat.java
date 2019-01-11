package soupit.recipe;


import java.util.ArrayList;

public class Zutat {
    private final String zutat;
    private final String grammatGeschlecht;
    private final String plural;
    private final ArrayList<String> synonyms;

    public Zutat(String zutat,String grammatGeschlecht, String plural,ArrayList<String> synonyms){
        this.zutat = zutat;
        this.grammatGeschlecht = grammatGeschlecht;
        this.plural = plural;
        this.synonyms = synonyms;
    }


   public boolean equals(Zutat z){
        if(z.toString().equals(zutat)) return true;
        if (this.synonyms.contains(z.toString())) return true;
        if(this.plural.equals(z.toString())) return true;
        return false;
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
