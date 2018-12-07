package soupit.HilfsKlassen;

public class Einheit {
    public final String einheit;
    public final String grammatGeschlecht;

    public Einheit(String einheit, String grammatGeschlecht){
        this.einheit = einheit;
        this.grammatGeschlecht = grammatGeschlecht;
    }

    @Override
    public String toString() {
        return einheit;
    }
}
