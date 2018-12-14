package soupit.recipe;

public class Rational {

    private int num, denom;

    public Rational(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
            d *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(d);
        this.num = num; this.denom = denom;
    }


    public String toString() {
        if(num<denom) {
            shorten();
            return String.valueOf(num) + "/" + String.valueOf(denom);
        }else if(num == denom){
            return "1";
        }else{
            String whole = String.valueOf(num/denom);
            String top = String.valueOf(num%denom);
            String bot = String.valueOf(denom);
            shorten();
            return String.valueOf(num/denom)+" "+String.valueOf(num%denom)+"/"+String.valueOf(denom);

        }
    }
    private void shorten(){
            num = num/gcm(denom,num);
            denom = denom/gcm(denom,num);
    }

    private int gcm(int a, int b) {
        return b == 0 ? a : gcm(b, a % b);
    }
}