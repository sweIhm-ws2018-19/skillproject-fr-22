package soupit.recipe;


public class ZutatMengeEinheit {
    private Zutat zutat;
    private double menge;
    private Einheit einheit;

    public ZutatMengeEinheit(soupit.recipe.Zutat zutat, double menge, soupit.recipe.Einheit einheit) {
        this.zutat = zutat;
        this.menge = menge;
        this.einheit = einheit;
    }

    public String zutatToString() {
        if (menge > 1) {
            return zutat.getPlural();
        }
        return zutat.toString();
    }

    public String mengeToString() {
        if (einheit.toString().equals("etwas")) return "";

        if (!einheit.toString().equals("null")) {
            if (einheit.getGrammatGeschlecht().equals("f")) {
                if (menge == 1) return "eine";
                else if (menge < 1) {
                    if (menge == 0.5) return "eine halbe";
                    else return "eine " + NumberToString();
                } else {
                    return NumberToString();
                }
            } else if (einheit.getGrammatGeschlecht().equals("m")) {
                if (menge == 1) return "einen";
                else if (menge < 1) {
                    if (menge == 0.5) return "einen halben";
                    else return "einen " + NumberToString();
                } else {
                    return NumberToString();

                }
            } else {
                if (menge == 1) return "ein";
                else if (menge < 1) {
                    if (menge == 0.5) return "ein halbes";
                    else return "ein " + NumberToString();
                } else {
                    return NumberToString();
                }
            }
        } else { //einheit null
            if (zutat.getGrammatGeschlecht().equals("f")) {
                if (menge == 1) return "eine";
                else if (menge < 1) {
                    if (menge == 0.5) return "eine halbe";
                    else return "eine " + NumberToString();
                } else {
                    return NumberToString();

                }
            } else if (zutat.getGrammatGeschlecht().equals("m")) {
                if (menge == 1) return "ein";
                else if (menge < 1) {
                    if (menge == 0.5) return "einen halben";
                    else return "einen " + NumberToString();
                } else {
                    return NumberToString();

                }
            } else {
                if (menge == 1) return "ein";
                else if (menge < 1) {
                    if (menge == 0.5) return "ein halbes";
                    else return "ein " + NumberToString();
                } else {
                    return NumberToString();
                }
            }
        }
    }

    public String einheitToString() {
        if (einheit.getEinheit().equals("null")) return "";
        if (menge > 1) return einheit.getPlural();
        return einheit.toString();
    }

    private String NumberToString() {
        int rounded = (int) menge;
        if (menge - rounded == 0) return String.valueOf((int) menge);
        if (rounded == 1) return "ein " + doubleToString(menge - rounded);
        if (rounded == 0) return doubleToString(menge - rounded);
        return rounded + " " + doubleToString(menge - rounded);


    }

    private String doubleToString(double number) {
        int roundedTo2Decimals = (int) (number * 100);

        if (menge < 1) {
            if (roundedTo2Decimals == 12) return "achtel";
            if (roundedTo2Decimals == 14) return "siebtel";
            if (roundedTo2Decimals == 16) return "sechstel";
            if (roundedTo2Decimals == 20) return "fünftel";
            if (roundedTo2Decimals == 25) return "viertel";
            if (roundedTo2Decimals == 33) return "drittel";
            //if (roundedTo2Decimals == 66) return "zwei drittel";
            //else return useRationalToGetString(number);
        }
        if (roundedTo2Decimals == 12) return "ein achtel";
        if (roundedTo2Decimals == 37) return "drei achtel";
        if (roundedTo2Decimals == 62) return "fünf achtel";
        if (roundedTo2Decimals == 87) return "sieben achtel";

        if (roundedTo2Decimals == 14) return "ein siebtel";
        if (roundedTo2Decimals == 28) return "zwei siebtel";
        if (roundedTo2Decimals == 42) return "drei siebtel";
        if (roundedTo2Decimals == 57) return "vier siebtel";
        if (roundedTo2Decimals == 71) return "fünf siebtel";
        if (roundedTo2Decimals == 85) return "sechs siebtel";

        if (roundedTo2Decimals == 16) return "ein sechstel";
        if (roundedTo2Decimals == 83) return "fünf sechstel";

        if (roundedTo2Decimals == 20) return "ein fünftel";
        if (roundedTo2Decimals == 40) return "zwei fünftel";
        if (roundedTo2Decimals == 60) return "drei fünftel";
        if (roundedTo2Decimals == 80) return "vier fünftel";

        if (roundedTo2Decimals == 25) return "ein viertel";
        if (roundedTo2Decimals == 75) return "drei viertel";

        if (roundedTo2Decimals == 33) return "ein drittel";
        if (roundedTo2Decimals == 66) return "zwei drittel";

        if (roundedTo2Decimals == 50) return "ein halb";



//        if (roundedTo2Decimals == 16) return "ein sechstel";// return "<say-as interpret-as=\"fraction\">" + "1/6" + "</say-as>";
//        else if (roundedTo2Decimals == 33) return "ein drittel"; //return "<say-as interpret-as=\"fraction\">" + "1/3" + "</say-as>";
//        else if (roundedTo2Decimals == 66) return "zwei drittel";//return "<say-as interpret-as=\"fraction\">" + "2/3" + "</say-as>";
//        else return useRationalToGetString(number);

        return "math error";

    }

    private String useRationalToGetString(double number) {
        Fraction fraction = new Fraction(number);
        if (fraction.getNum() == 1 && menge < 1) {
            return fraction.getDenom() + "tel";
        } else {
            String fractionString = fraction.toString();
            return "<say-as interpret-as=\"fraction\">" + fractionString + "</say-as>";
        }
    }

    public Zutat getZutat() {
        return zutat;
    }

    public void multiplyIngredients(double mulitplicator) {
        this.menge *= mulitplicator;
    }


}
