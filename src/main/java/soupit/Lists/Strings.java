package soupit.Lists;

public class Strings {
    public static final String TEMPLATE = "";

    private static final String [] WELCOME ={ "Willkommen bei Soup It. Als dein persönlicher Assistent begleite ich dich bei der Suppenzubereitung."
            ,"Ich begleite dich als dein persönlicher Kochassistent bei der Suppenzubereitung."
            ,"Ich werde dich als persönlicher Kochassistent bei der Zubereitung einer Suppe begleiten."};
   private static final String[] FINISHED_COOKING = {" Lass dir die Suppe schmecken."," Ich wünsch dir einen guten Appetit. Bis zum nächsten Mal."," Ich wünsche einen guten Appetit. Lass dir die Suppe schmecken.",
            " Einen guten Appetit.", " Ich hoffe die Suppe schmeckt und wünsche einen guten Appetit. Bis zum nächsten Mal."};
    public static final String REPROMPT = "Sage eine Zutat und ich schlage dir ein Supppenrezept vor";
    public static final String NAME_OF_SOUP_STATE = "NameOfSoupState";
    public static final String INITIAL_STATE = "InitialState";
    public static final String SOUP_YES_NO_STATE = "SoupYesNoState";
    public static final String INGREDIENTSAVAILIABLE= "IngredientsAvaliable";
    public static final String CONTINUE_SOUP_YES_NO_STATE = "ContinueSoupYesNo";
    public static final String COOKING_STATE = "CookingState";
    public static final String INGREDIENT_NAMED_STATE = "IngredientNamed";
    public static final String STARTCOOKING_STATE = "StartCookingState";
    public static final String RECIPE_CHOSEN_STATE = "RecipeChosenState";
    public static final String RESTART_YES_NO_STATE= "RestartYesNoState";

    public static String getRandomWelcome(){
        String[] whichIngredients = {"Nenne mir Zutaten, die du zum Kochen verwenden möchtest.","Welche Zutaten möchtest du verwenden?","Mit welchen Zutaten möchtest du eine Suppe kochen?"};
        return WELCOME[(int) (Math.random()*WELCOME.length)].concat(" ").concat( whichIngredients[(int) (Math.random()*whichIngredients.length)] );
    }

    public static String getRandomFinish(){
        return FINISHED_COOKING[(int)(Math.random()*FINISHED_COOKING.length)];
    }



}