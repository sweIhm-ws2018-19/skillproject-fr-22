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
                    else string = "eine " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }                                                    //TODO 2 1/3 != 2.33333333 !
            }else if (einheit.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )string = "einen";
                else if(menge<1){
                    if(menge== 0.5)string = "einen halben";
                    else if(menge == 0.3) string = "einen drittel";
                    else string = "einen " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }                                               //TODO
            }else{
                if(menge == 1) string = "ein";
                else if(menge< 1){
                    if (menge == 0.5) string = "ein halbes";
                    else if (menge == 0.3) string = "ein drittel";
                    else string = "ein " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }
            }
        }else{ //einheit null
            if (zutat.getGrammatGeschlecht().equals("f")){
                if(menge == 1) string = "eine";
                else if (menge<1){
                    if(menge == 0.5) string = "eine halbe";
                    else if (menge == 0.3) string = "eine drittel";
                    else string = "eine " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }                                               //TODO
            }else if (zutat.getGrammatGeschlecht().equals("m")){
                if(menge == 1 )string = "ein";
                else if(menge<1){
                    if(menge== 0.5)string = "einen halben";
                    else if(menge == 0.3) string = "einen drittel";
                    else string = "einen " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }                                                //TODO
            }else{
                if(menge == 1) string = "ein";
                else if(menge< 1){
                    if (menge == 0.5) string = "ein halbes";
                    else if (menge == 0.3) string = "ein drittel";
                    else string = "ein " +(int)(1/menge)+"tel";
                }else {
                    if(menge - (int) menge == 0 ) string =""+(int)menge;
                    else string = ""+menge;                                               //TODO
                }                                               //TODO
            }
        }
        if(string.contains(".")){
           string =  string.replaceAll("\\.","komma");
        }
        return string;
    }

    public String einheitToString(){
        return einheit.getEinheit().equals("null")?"":einheit.toString();
    }

    private String fractionToString(double number){


        return null;
    }

    public Zutat getZutat() {
        return zutat;
    }
}
