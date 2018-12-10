package soupit.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import soupit.recipe.*;
import soupit.Lists.Strings;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;
import static soupit.handlers.LaunchRequestHandler.REZEPT_ARRAY_LIST;

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
        if (ingredientSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String[] strinGredients = ingredientSlot.getValue().split("\\s");
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for (int i = 0; i < strinGredients.length; i++) {
                ingredients[i] = new Zutat(strinGredients[i], "f");
            }

            input.getAttributesManager().setSessionAttributes(Collections.singletonMap("Ingredient", ingredients));


            NavigableMap<Integer, ArrayList<Rezept>> map = REZEPT_ARRAY_LIST.getFitting(ingredients);
            ArrayList<Rezept> list = treeMapToSortedList(map);
            if (!list.isEmpty()) {
                speechText = new StringBuilder("Mit diesen Zutaten kannst du ");
                int listSize = list.size();
                if (listSize == 1) {
                    speechText.append("eine ");
                    speechText.append(list.get(0));
                    speechText.append(" kochen. möchtest du das tun ?");
                    ////PROGRAMSTATE COOK_SOUP_YES_NO
                }
                if (listSize <= 3) {
                    for (int i = 0; i < listSize; i++) {
                        speechText.append("eine ");
                        if (i < listSize - 2) {
                            speechText.append(list.get(i));
                            speechText.append(", ");
                        } else if (i == listSize - 2) {
                            speechText.append(list.get(i));
                            speechText.append(" oder ");
                        } else {
                            speechText.append(list.get(i));
                            speechText.append(" kochen. ");
                            speechText.append("Sag mir den Namen der Suppe die du kochen möchtest ");
                            ////PROGRAMSTATE GET_NAME_OF_SOUP
                        }
                    }
                } else {
                    speechText = new StringBuilder(" für diese Zutaten fallen mir ");
                    speechText.append(listSize);
                    speechText.append(" Rezepte ein. zu deinen Zutaten passen am besten die folgenden 3 Rezepte: ");
                    speechText.append(list.get(0));
                    speechText.append(", ");
                    speechText.append(list.get(1));
                    speechText.append("und ");
                    speechText.append(list.get(2));
                    speechText.append(" wenn dir eins davon gefällt sage mir den Namen des Rezeptes, oder weiter, wenn du mehr hören möchtest ");


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
        }



        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSpeech(speechText.toString())
                .withShouldEndSession(false);
        return responseBuilder.build();
    }

    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {                              //TODO Refactor this method to be in some other class
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }

}