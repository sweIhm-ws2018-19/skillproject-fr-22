package soupit;

import soupit.Lists.Strings;
import soupit.recipe.Rezept;

import java.util.ArrayList;
import java.util.List;

public class SessionAttributes {
    public static ArrayList<Rezept> arrayList;
    public static List matchingRecipes;
    public static int matchingRecipesIndex;
    public static Rezept recipeToDecideOn;
    public static Rezept currentRecipe;
    public static String programState;

    public static void clear() {
        arrayList.clear();
        matchingRecipes.clear();
        recipeToDecideOn = null;
        currentRecipe = null;
        programState = Strings.INITIAL_STATE;
        matchingRecipesIndex = 0;
    }
}
