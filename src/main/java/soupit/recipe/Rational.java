package soupit.recipe;

public class Rational {

    private int num = 1;
    private int denom =1;

    public Rational(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
            d *= 10;
            denom *= 10;
        }
        int test  = (int) d;
        this.num = (int ) Math.round(d);
        this.denom = denom;

    }


    public String toString() {
        if(num<denom) {
            shorten();
            return String.valueOf(num) + "/" + String.valueOf(denom);
        }else if(num == denom){
            return "1";
        }else{
            shorten();
            return String.valueOf(num/denom)+" "+String.valueOf(num%denom)+"/"+String.valueOf(denom);

        }
    }
    private void shorten(){
        int gcm = gcm(denom,num);
            num = num/gcm;
            denom = denom/gcm;
    }

    private int gcm(int a, int b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    public int getNum(){
        shorten();
        return num;
    }
}