package soupit.recipe;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Slot;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.handlers.IngredientIntentHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import static soupit.SessionAttributes.strinGredients;

public class IngredientIntentManager {

    public static StringBuilder getSpeechResponse(HandlerInput input, Slot ingredientSlot,String invocingClass) {
        StringBuilder speechText;
            IngredientIntentHandler.setProgramState = true;
            IngredientIntentHandler.setLastSentence= true;



            String[] strinGredients = ingredientSlot.getValue().split("\\s");
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for (int i = 0; i < strinGredients.length; i++) {
                ingredients[i] = new Zutat(strinGredients[i], "gender","plural",new ArrayList<>());
            }

            if(SessionAttributes.programState.equals(Strings.INITIAL_STATE)) {

                NavigableMap<Integer, ArrayList<Rezept>> map = SessionAttributes.recipes.getFitting(ingredients);
                ArrayList<Rezept> listwithAll = treeMapToSortedList(map);
                List<Rezept> list;
                if (listwithAll.size() > 6) {
                    list = listwithAll.subList(0, 6);
                } else {
                    list = listwithAll;
                }

                if (!list.isEmpty()) {
                    speechText = new StringBuilder("ich kann dir anhand der genannten Zutaten ");
                    int listSize = list.size();
                    if (listSize == 1) speechText.append("<emphasis level=\"strong\">ein</emphasis>");
                    else speechText.append(listSize);
                    speechText.append(" Rezept");
                    if (listSize > 1) speechText.append("e");
                    speechText.append(" vorschlagen: ");
                    if (listSize == 1) {
                        speechText.append(list.get(0));
                        speechText.append(". Möchtest du diese Suppe kochen?");
                        SessionAttributes.recipeToDecideOn = list.get(0);
                        SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;
                        PersistentAttributes.setProgramState(Strings.SOUP_YES_NO_STATE, input);
                        PersistentAttributes.setRecipeToDecideOn(input);
                        IngredientIntentHandler.setProgramState = false;
                    } else if (listSize <= 3) {
                        for (int i = 0; i < listSize; i++) {
                            speechText.append(list.get(i));
                            if (i < listSize - 2) {
                                speechText.append(", ");
                            } else if (i == listSize - 2) {
                                speechText.append(", oder ");
                            } else {
                                speechText.append(". Welche suppe wählst du?");
                            }
                        }
                    } else {
                        speechText.append(list.get(0));
                        speechText.append(", ");
                        speechText.append(list.get(1));
                        speechText.append(", ");
                        speechText.append(list.get(2));
                        speechText.append(" und ");
                        int remainingRecipesCount = listSize - 3;
                        if (remainingRecipesCount == 1) speechText.append("ein weiteres");
                        else {
                            speechText.append(remainingRecipesCount);
                            speechText.append(" weitere");
                        }
                        speechText.append(". ");
                        speechText.append("wähle eine Suppe oder sage: weitere anhören ");
                        SessionAttributes.matchingRecipes = list;
                        SessionAttributes.matchingRecipesIndex = 3;


                    }
                } else {
                        speechText = new StringBuilder("für deine Zutat");
                        if (strinGredients.length > 1) speechText.append("en");
                        speechText.append("<break time=\"300ms\"/> ");
                        for (int i = 0; i < strinGredients.length; i++) {
                            speechText.append(strinGredients[i]);
                            if (i < strinGredients.length - 2) speechText.append(", ");
                            else if (i == strinGredients.length - 2) speechText.append(" und ");
                        }
                        speechText.append(",  kann ich dir aktuell leider kein passendes Suppenrezept vorschlagen.");
                        if (SessionAttributes.userAnnoyance == 0) {
                            speechText.append(" Nenne mir andere Zutaten oder lasse dich von mir inspirieren. ");
                            SessionAttributes.userAnnoyance++;
                        }


                    IngredientIntentHandler.setProgramState = false;
                    IngredientIntentHandler.setLastSentence = false;
                }
            } else{
                speechText = new StringBuilder("bist du dir sicher, dass du eine neue Suppe mit ");
                if(strinGredients.length == 1) {
                    speechText.append(strinGredients[0]);
                    speechText.append(" kochen willst?");
                }else{
                    for(int i =0; i<strinGredients.length; i++){
                        speechText.append(strinGredients[i]);
                        if(i < strinGredients.length -2) speechText.append(", ");
                       else  if(i == strinGredients.length -2 )speechText.append(" und ");
                    }
                    speechText.append(" kochen willst? ");

                }
                SessionAttributes.beforeRestartState = SessionAttributes.programState;
                IngredientIntentHandler.setProgramState = false;
                IngredientIntentHandler.setLastSentence = false;
                SessionAttributes.programState = Strings.SOUP_WITH_NEW_INGREDIENTS_YES_NO_STATE;
                PersistentAttributes.setProgramState(Strings.SOUP_WITH_NEW_INGREDIENTS_YES_NO_STATE,input);
                SessionAttributes.strinGredients = strinGredients;
            }


 return speechText;
    }

    public static StringBuilder getSpeechResponse(HandlerInput input){
        StringBuilder speechText;
        IngredientIntentHandler.setProgramState = true;
        IngredientIntentHandler.setLastSentence= true;




        Zutat[] ingredients = new Zutat[strinGredients.length];
        for (int i = 0; i < strinGredients.length; i++) {
            ingredients[i] = new Zutat(strinGredients[i], "gender","plural",new ArrayList<>());
        }


            NavigableMap<Integer, ArrayList<Rezept>> map = SessionAttributes.recipes.getFitting(ingredients);
            ArrayList<Rezept> listwithAll = treeMapToSortedList(map);
            List<Rezept> list;
            if (listwithAll.size() > 6) {
                list = listwithAll.subList(0, 6);
            } else {
                list = listwithAll;
            }

            if (!list.isEmpty()) {
                speechText = new StringBuilder("ich kann dir anhand der genannten Zutaten ");
                int listSize = list.size();
                if (listSize == 1) speechText.append("<emphasis level=\"strong\">ein</emphasis>");
                else speechText.append(listSize);
                speechText.append(" Rezept");
                if (listSize > 1) speechText.append("e");
                speechText.append(" vorschlagen: ");
                if (listSize == 1) {
                    speechText.append(list.get(0));
                    speechText.append(". Möchtest du diese Suppe kochen?");
                    SessionAttributes.recipeToDecideOn = list.get(0);
                    SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;
                    PersistentAttributes.setProgramState(Strings.SOUP_YES_NO_STATE, input);
                    PersistentAttributes.setRecipeToDecideOn(input);
                    IngredientIntentHandler.setProgramState = false;
                } else if (listSize <= 3) {
                    for (int i = 0; i < listSize; i++) {
                        speechText.append(list.get(i));
                        if (i < listSize - 2) {
                            speechText.append(", ");
                        } else if (i == listSize - 2) {
                            speechText.append(", oder ");
                        } else {
                            speechText.append(". Welche suppe wählst du?");
                        }
                    }
                } else {
                    speechText.append(list.get(0));
                    speechText.append(", ");
                    speechText.append(list.get(1));
                    speechText.append(", ");
                    speechText.append(list.get(2));
                    speechText.append(" und ");
                    int remainingRecipesCount = listSize - 3;
                    if (remainingRecipesCount == 1) speechText.append("ein weiteres");
                    else {
                        speechText.append(remainingRecipesCount);
                        speechText.append(" weitere");
                    }
                    speechText.append(". ");
                    speechText.append("wähle eine Suppe oder sage: weitere anhören ");
                    SessionAttributes.matchingRecipes = list;
                    SessionAttributes.matchingRecipesIndex = 3;


                }
                SessionAttributes.programState = Strings.INGREDIENT_NAMED_STATE;
                PersistentAttributes.setProgramState(Strings.INGREDIENT_NAMED_STATE,input);
            } else {
                speechText = new StringBuilder("für deine Zutat");
                if (strinGredients.length > 1) speechText.append("en");
                speechText.append("<break time=\"300ms\"/> ");
                for (int i = 0; i < strinGredients.length; i++) {
                    speechText.append(strinGredients[i]);
                    if (i < strinGredients.length - 2) speechText.append(", ");
                    else if (i == strinGredients.length - 2) speechText.append(" und ");
                }
                speechText.append(",  kann ich dir aktuell leider kein passendes Suppenrezept vorschlagen.");
                if (SessionAttributes.userAnnoyance == 0) {
                    speechText.append(" Nenne mir andere Zutaten oder lasse dich von mir inspirieren. ");
                    SessionAttributes.userAnnoyance++;
                }


            }


        return speechText;
    }

    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }


}
