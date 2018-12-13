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
import soupit.SessionAttributes;
import soupit.recipe.*;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

public class IngredientIntentHandler implements RequestHandler {
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


        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSpeech(speechText.toString())
                .withShouldEndSession(false);
        return responseBuilder.build();
    }

    private StringBuilder getSpeechResponse(HandlerInput input, Slot ingredientSlot) {
        StringBuilder speechText;
        if (ingredientSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String[] strinGredients = ingredientSlot.getValue().split("\\s");
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for (int i = 0; i < strinGredients.length; i++) {
                ingredients[i] = new Zutat(strinGredients[i], "f");
            }

            input.getAttributesManager().setSessionAttributes(Collections.singletonMap("Ingredient", ingredients));


            NavigableMap<Integer, ArrayList<Rezept>> map = SessionAttributes.recipes.getFitting(ingredients);
            ArrayList<Rezept> listwithAll = treeMapToSortedList(map);
            List<Rezept> list;
            if (listwithAll.size() > 6) {
                list = listwithAll.subList(0,7);
            }else {
                list = listwithAll;
            }

            if (!list.isEmpty()) {
                speechText = new StringBuilder("ich kann dir anhand der genannten Zutaten ");
                int listSize = list.size();
                speechText.append(listSize);
                speechText.append(" Rezept");
                if(listSize > 1) speechText.append("e");
                speechText.append(" vorschlagen: ");
                if (listSize == 1) {
                   speechText.append(list.get(0));
                   speechText.append(" Möchtest du diese Suppe kochen?");
                   SessionAttributes.recipeToDecideOn= list.get(0);
                   SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;

                }
                else if (listSize <= 3) {
                    for (int i = 0; i < listSize; i++) {
                        speechText.append(list.get(i));
                        if (i < listSize - 2) {
                            speechText.append(", ");
                        } else if (i == listSize - 2) {
                            speechText.append(" oder ");
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
                    speechText.append(remainingRecipesCount);
                    speechText.append(" weitere");
                    if (remainingRecipesCount == 1) speechText.append("s");
                    speechText.append(". ");
                    speechText.append("wähle eine Suppe oder sage: weitere anhören ");
                    SessionAttributes.matchingRecipes = list;
                    SessionAttributes.matchingRecipesIndex = 3;


//              Iterator<Map.Entry<Integer,ArrayList<Rezept>>> i = descendingMap.entrySet().iterator();
//              while (i.hasNext()){
//                  ArrayList<Rezept> list = i.next().getValue();
//                  for(Rezept r:list){
//
//                  }
//
//              }


//            speechText += "dafür brauchst du ";
//            for(int i =0; i<bestRecipe.zumeng.length; i++) {
//                ZutatMengeEinheit zum = bestRecipe.zumeng[i];
//                if(i == bestRecipe.zumeng.length -1) speechText += " und ";
//                speechText += zum.mengeToString() + " ";
//                speechText += zum.einheitToString() + " ";
//                speechText += zum.zutatToString() + " <break time=\"1s\"/>";
//                if (i < bestRecipe.zumeng.length -2) speechText += ", ";
//                if(i == bestRecipe.zumeng.length -1) speechText += ". ";

                }
            } else {
                speechText = new StringBuilder("Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ");
            }





        }else{
            speechText = new StringBuilder("tut mir leid, das habe ich nicht verstanden. kannst du das wiederholen ?");
        } return speechText;
    }

    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {                              //TODO Refactor this method to be in some other class
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }

}