package soupit.recipe;

import java.util.*;

public class RezeptArrayList extends ArrayList<Rezept> {
    public RezeptArrayList(Rezept...r){
        super.addAll(Arrays.asList(r));
    }

    public NavigableMap<Integer,ArrayList<Rezept>> getFitting(Zutat...ingredients) {
        TreeMap<Integer,ArrayList<Rezept>> recipeToAccordance = new TreeMap<>();
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
        return recipeToAccordance.descendingMap();
    }

    public Rezept find(String recipeName){
        String recipenameNoSpaces = recipeName.replaceAll("\\s","");
        for(Rezept r:this){
            if(r.toString().equals(recipenameNoSpaces)) return r;
        }
        return null;
    }
}
