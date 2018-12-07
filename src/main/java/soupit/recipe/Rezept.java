package soupit.recipe;

import java.util.Arrays;

public class Rezept {
    public ZutatMengeEinheit[] zumeng;
    String name;
    ZutatMengeEinheit[] optionaleZutaten;

    public  Rezept(String name, ZutatMengeEinheit...zumeng){
        this.zumeng = zumeng;
        this.name = name;
    }


    int checkAccordance(Zutat...z){
        int count =0;
        for(Zutat zut:z){
            for(ZutatMengeEinheit zum:zumeng){
                if (zum.zutat.equals(zut))count++;
            }
        }
        return count;
    }

    public void addOptions(ZutatMengeEinheit...optionaleZutaten){
        this.optionaleZutaten = Arrays.copyOf(optionaleZutaten,optionaleZutaten.length);
    }


    public String toString() {
        return name;
    }
}
