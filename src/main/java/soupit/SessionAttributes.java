package soupit;

import soupit.Lists.Strings;
import soupit.recipe.Rezept;

import java.util.ArrayList;

public class SessionAttributes {
    ArrayList<Rezept> arrayList;
    Rezept recipeToDecideOn;
    Rezept currentRecipe;
    String programState;

    void clear(){
        this.arrayList.clear();
        recipeToDecideOn = null;
        currentRecipe = null;
        programState = Strings.INITIAL_STATE;
    }
}
