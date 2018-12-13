package soupit;

import soupit.Lists.Strings;
import soupit.recipe.Rezept;
import soupit.recipe.RezeptArrayList;

import java.util.List;

public class SessionAttributes {
    public static RezeptArrayList recipes;
    public static List matchingRecipes;
    public static int matchingRecipesIndex;
    public static Rezept recipeToDecideOn;
    public static Rezept currentRecipe;
    public static String programState;

    public static void clear() {
        recipes.clear();
        matchingRecipes.clear();
        recipeToDecideOn = null;
        currentRecipe = null;
        programState = Strings.INITIAL_STATE;
        matchingRecipesIndex = 0;
    }


    public static void setCurrentRecipe(){

    }
}
