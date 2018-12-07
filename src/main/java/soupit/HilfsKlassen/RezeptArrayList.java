package soupit.HilfsKlassen;

import java.util.ArrayList;
import java.util.Arrays;

public class RezeptArrayList extends ArrayList<Rezept> {
    public RezeptArrayList(Rezept...r){
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
