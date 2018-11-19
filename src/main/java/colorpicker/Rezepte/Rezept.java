package main.java.colorpicker.Rezepte;

public abstract class Rezept {
    public ZutatMenge[] zumeng;
    String name;

    public  Rezept(String name, ZutatMenge...zumeng){
        this.zumeng = zumeng;
        this.name = name;
    }

    int checkAccordance(Zutat...z){
        int count =0;
        for(Zutat zut:z){
            for(ZutatMenge zum:zumeng){
                if (zum.zutat.equals(zut))count++;
            }
        }
        return count;
    }


    public String toString() {
        return name;
    }
}
