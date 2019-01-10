package soupit;

import soupit.Lists.Strings;
import soupit.recipe.Rezept;
import soupit.recipe.RezeptArrayList;
import soupit.recipe.ZutatMengeEinheit;

import java.util.List;
import java.util.Map;

public class SessionAttributes {
    public static RezeptArrayList recipes = new RezeptArrayList();
    public static List matchingRecipes;
    public static int matchingRecipesIndex;
    public static Rezept recipeToDecideOn;
    public static Rezept currentRecipe;
    public static String programState;
    public static String[] steps;
    public static int stepTracker;
    public static Map<String,String[]> synonyme;

    public static void clear(boolean clearRecipes) {
        if(clearRecipes) recipes.clear();
        recipeToDecideOn = null;
        currentRecipe = null;
        programState = Strings.INITIAL_STATE;
        matchingRecipesIndex = 0;
        stepTracker = 0;
    }


    public static boolean setCurrentRecipe(String recipeName){
        if(recipes.find(recipeName) == null){
            return false;
        }
        currentRecipe = recipes.find(recipeName);
        steps = currentRecipe.getSteps();
        return true;
    }


    public static ZutatMengeEinheit[] getCurrentRecipeZumeng(){
        return currentRecipe.getZumeng();
    }
}
