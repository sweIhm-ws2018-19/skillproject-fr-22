package soupit.recipe;

public class Einheit {
    private final String einheit;
    private final String grammatGeschlecht;
    private final String plural;


    public Einheit(String einheit, String grammatGeschlecht, String plural){
        this.einheit = einheit;
        this.grammatGeschlecht = grammatGeschlecht;
        this.plural = plural;
    }

    @Override
    public String toString() {
        return einheit;
    }

    String getEinheit() {
        return einheit;
    }

    String getGrammatGeschlecht() {
        return grammatGeschlecht;
    }

    String getPlural(){
        return plural;
    }

}
