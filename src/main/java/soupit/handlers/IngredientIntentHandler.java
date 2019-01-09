package soupit.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.recipe.*;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

public class IngredientIntentHandler implements RequestHandler {

    boolean setProgramState;
    boolean setLastSentence;
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("IngredientIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from the list of slots.
        Slot ingredientSlot = slots.get("Ingredient");

        StringBuilder speechText;


        // Check for favorite color and create output to user.
        speechText = getSpeechResponse(input, ingredientSlot);


        if(setProgramState) PersistentAttributes.setProgramState(Strings.INGREDIENT_NAMED_STATE,input);
        if(setLastSentence) PersistentAttributes.setLastSentence(speechText.toString(),input);

        ResponseBuilder responseBuilder = input.getResponseBuilder();
        responseBuilder.withSpeech(speechText.toString())
                .withShouldEndSession(false);
        return responseBuilder.build();
    }

    private StringBuilder getSpeechResponse(HandlerInput input, Slot ingredientSlot) {
        StringBuilder speechText;
        setProgramState = true;
        setLastSentence = true;
        if (ingredientSlot != null) {

            String[] strinGredients = ingredientSlot.getValue().split("\\s");
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for (int i = 0; i < strinGredients.length; i++) {
                ingredients[i] = new Zutat(strinGredients[i], "gender","plural");
            }



            NavigableMap<Integer, ArrayList<Rezept>> map = SessionAttributes.recipes.getFitting(ingredients);
            ArrayList<Rezept> listwithAll = treeMapToSortedList(map);
            List<Rezept> list;
            if (listwithAll.size() > 6) {
                list = listwithAll.subList(0,6);
            }else {
                list = listwithAll;
            }

            if (!list.isEmpty()) {
                speechText = new StringBuilder("ich kann dir anhand der genannten Zutaten ");
                int listSize = list.size();
                if (listSize == 1)speechText.append("<emphasis level=\"strong\">ein</emphasis>");
                else speechText.append(listSize);
                speechText.append(" Rezept");
                if(listSize > 1) speechText.append("e");
                speechText.append(" vorschlagen: ");
                if (listSize == 1) {
                   speechText.append(list.get(0));
                   speechText.append(". Möchtest du diese Suppe kochen?");
                   SessionAttributes.recipeToDecideOn= list.get(0);
                   SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;
                   PersistentAttributes.setProgramState(Strings.SOUP_YES_NO_STATE,input);
                   PersistentAttributes.setRecipeToDecideOn(input);
                   setProgramState = false;
                }
                else if (listSize <= 3) {
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
                    int remainingRecipesCount = listSize-3;
                    if(remainingRecipesCount == 1) speechText.append("ein weiteres");
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
                speechText = SessionAttributes.programState.equals(Strings.INITIAL_STATE) ? new StringBuilder("Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! "): new StringBuilder("Das habe ich leider nicht verstanden , kannst du das wiederholen?");
                setProgramState = false;
                setLastSentence = false;
            }





        }else{
            speechText = new StringBuilder("tut mir leid, das habe ich nicht verstanden. kannst du das wiederholen ?");
        } return speechText;
    }

    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }

}