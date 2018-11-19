package main.java.colorpicker.Rezepte;

import java.util.ArrayList;
import java.util.Arrays;

public class Rezepte extends ArrayList<Rezept> {
    public Rezepte (Rezept...r){
        super.addAll(Arrays.asList(r));
    }

    public Rezept getBestFitting(Zutat...ingredients) {
        int bestAccordance = 0;
        Rezept bestRecipe = null;
        for(Rezept r:this){
            int accordance = r.checkAccordance(ingredients);
            if(accordance > bestAccordance){
                bestAccordance = accordance;
                bestRecipe = r;
            }
        }
        return bestRecipe;
    }
}
