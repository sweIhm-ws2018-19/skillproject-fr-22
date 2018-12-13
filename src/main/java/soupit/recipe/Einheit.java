package soupit.recipe;

public class Einheit {
    private final String einheit; //private setzen
    private final String grammatGeschlecht;

    public Einheit(String einheit, String grammatGeschlecht){
        this.einheit = einheit;
        this.grammatGeschlecht = grammatGeschlecht;
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

}
