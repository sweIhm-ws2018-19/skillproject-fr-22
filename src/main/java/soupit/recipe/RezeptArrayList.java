package soupit.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RezeptArrayList extends ArrayList<Rezept> {
    public RezeptArrayList(Rezept...r){
        super.addAll(Arrays.asList(r));
    }

    public Map<Integer,ArrayList<Rezept>> getFitting(Zutat...ingredients) {
        Map<Integer,ArrayList<Rezept>> recipeToAccordance = new HashMap<>();
        for(Rezept r:this){
            int accordance = r.checkAccordance(ingredients);
            if(accordance > 0){
                if(recipeToAccordance.get(accordance) == null){
                    recipeToAccordance.put(accordance,new ArrayList<>());
                    recipeToAccordance.get(accordance).add(r);
                }else{
                    recipeToAccordance.get(accordance).add(r);
                }
            }
        }
        return recipeToAccordance;
    }
}
