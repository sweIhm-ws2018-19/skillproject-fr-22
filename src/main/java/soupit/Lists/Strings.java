package soupit.Lists;

public class Strings {
    public static final String TEMPLATE = "";

    public static final String [] WELCOME ={ "Willkommen bei Soup It. Als dein persönlicher Assistent begleite ich dich bei der Suppenzubereitung."
            ,"Ich begleite dich als dein persönlicher Kochassistent bei der Suppenzubereitung."
            ,"Ich werde dich als persönlicher Kochassistent bei der Zubereitung einer Suppe begleiten."};
    public static final String REPROMPT = "Sage eine Zutat und ich schlage dir ein Supppenrezept vor";
    public static final String NAME_OF_SOUP_STATE = "NameOfSoupState";
    public static final String INITIAL_STATE = "InitialState";
    public static final String SOUP_YES_NO_STATE = "SoupYesNoState";
    public static final String INGREDIENTSAVAILIABLE= "IngredientsAvaliable";
    public static final String CONTINUE_SOUP_YES_NO_STATE = "ContinueSoupYesNo";
    public static final String COOKING_STATE = "CookingState";
    public static final String INGREDIENT_NAMED_STATE = "IngredientNamed";

    public static String getRandomWelcome(){
        return WELCOME[(int) (Math.random()*3)];
    }



}