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
        String string;
        if (! einheit.toString().equals("null")) {
            if (einheit.getGrammatGeschlecht().equals("f")){
                if(menge == 1) string = "eine";
                else if (menge<1){
                    if(menge == 0.5) string = "eine halbe";
                    else if (menge == 0.3) string = "eine drittel";
                    else if(menge == 0.15) string ="eine sekstel";
                    else string = "eine " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);
                }
            }else if (einheit.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )string = "einen";
                else if(menge<1){
                    if(menge== 0.5)string = "einen halben";
                    else if(menge == 0.3) string = "einen drittel";
                    else if(menge == 0.15) string ="einen sekstel";
                    else string = "einen " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);

                }
            }else{
                if(menge == 1) string = "ein";
                else if(menge< 1){
                    if (menge == 0.5) string = "ein halbes";
                    else if (menge == 0.3) string = "ein drittel";
                    else if(menge == 0.15) string ="ein sekstel";
                    else string = "ein " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);

                }
            }
        }else{ //einheit null
            if (zutat.getGrammatGeschlecht().equals("f")){
                if(menge == 1) string = "eine";
                else if (menge<1){
                    if(menge == 0.5) string = "eine halbe";
                    else if (menge == 0.3) string = "eine drittel";
                    else if(menge == 0.15) string ="eine sekstel";
                    else string = "eine " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);

                }
            }else if (zutat.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )string = "ein";
                else if(menge<1){
                    if(menge== 0.5)string = "einen halben";
                    else if(menge == 0.3) string = "einen drittel";
                    else if(menge == 0.15) string ="einen sekstel";
                    else string = "einen " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);

                }
            }else{
                if(menge == 1) string = "ein";
                else if(menge< 1){
                    if (menge == 0.5) string = "ein halbes";
                    else if (menge == 0.3) string = "ein drittel";
                    else if(menge == 0.15) string ="ein sekstel";
                    else string = "ein " +doubletToString(menge);
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = doubletToString(menge);
                }
            }
        }
        return string;
    }

    public String einheitToString(){
        return einheit.getEinheit().equals("null")?"":einheit.toString();
    }

    private String doubletToString(double number){
        Rational rational = new Rational(number);
        if(rational.getNum() == 1){
            return String.valueOf((int) (1/menge))+"tel";
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
