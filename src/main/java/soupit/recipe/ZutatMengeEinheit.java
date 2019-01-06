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
        String string = zutat.toString();
        if (menge > 1) {
            if (zutat.toString().equals("kartoffel") || zutat.toString().equals("zwiebel")) {
                string += "n";
            }
        }
        return string;
    }

    public String mengeToString() {

        if (! einheit.toString().equals("null")) {
            if (einheit.getGrammatGeschlecht().equals("f")){
                if(menge == 1) return "eine";
                else if (menge<1){
                    if(menge == 0.5) return  "eine halbe";
                    else return "eine " +NumberToString();
                }else {
                    return NumberToString();
                }
            }else if (einheit.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )return  "einen";
                else if(menge<1){
                    if(menge== 0.5)return  "einen halben";
                    else return  "einen " +NumberToString();
                }else {
                    return NumberToString();

                }
            }else{
                if(menge == 1) return  "ein";
                else if(menge< 1){
                    if (menge == 0.5) return  "ein halbes";
                    else return  "ein " +NumberToString();
                }else {
                    return NumberToString();
                }
            }
        }else{ //einheit null
            if (zutat.getGrammatGeschlecht().equals("f")){
                if(menge == 1) return  "eine";
                else if (menge<1){
                    if(menge == 0.5) return  "eine halbe";
                    else return  "eine " +NumberToString();
                }else {
                return NumberToString();

                }
            }else if (zutat.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )return  "ein";
                else if(menge<1){
                    if(menge== 0.5)return  "einen halben";
                    else return  "einen " +NumberToString();
                }else {
                    return NumberToString();

                }
            }else{
                if(menge == 1) return  "ein";
                else if(menge< 1){
                    if (menge == 0.5) return  "ein halbes";
                    else return  "ein " +NumberToString();
                }else {
                    return NumberToString();
                }
            }
        }
    }

    public String einheitToString(){
        if(einheit.getEinheit().equals("null")) return "";
        if(menge>1) return einheit.getPlural();
        return einheit.toString();
    }

    private String NumberToString(){
        int rounded = (int) menge;
        if(menge - rounded == 0 ) return  String.valueOf((int)menge);
        else{

            if(rounded == 1) return  "ein "+doubleToString(menge - rounded);
            else {
                return  "" + rounded + "" + doubleToString(menge - rounded);
            }
        }

    }

    private String doubleToString(double number) {
        int roundedTo2Decimals = (int) (number*100);

        if (menge < 1 ) {
            if (roundedTo2Decimals == 16) return "sekstel";
            else if (roundedTo2Decimals == 33) return "drittel";
            else if (roundedTo2Decimals == 66) return "zwei drittel";
            else return useRationalToGetString(number);
        }
         else {
            if (roundedTo2Decimals == 16) return "<say-as interpret-as=\"fraction\">" + "1/6" + "</say-as>";
            else if (roundedTo2Decimals == 33) return "<say-as interpret-as=\"fraction\">" + "1/3" + "</say-as>";
            else if (roundedTo2Decimals == 66) return "<say-as interpret-as=\"fraction\">" + "2/3" + "</say-as>";
            else return useRationalToGetString(number);
        }
    }

    private String useRationalToGetString(double number) {
        Rational rational = new Rational(number);
        if(rational.getNum() == 1 && menge < 1){
            return rational.getDenom()+"tel";
        }
        else {
            String fraction = rational.toString();
            return "<say-as interpret-as=\"fraction\">" + fraction + "</say-as>";
        }
    }

    public Zutat getZutat() {
        return zutat;
    }

    public void multiplyIngredients(int mulitplicator){
        this.menge*=mulitplicator;
    }


}
