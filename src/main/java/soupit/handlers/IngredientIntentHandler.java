package main.java.soupit.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import main.java.soupit.HilfsKlassen.ZutatMengeEinheit;
import main.java.soupit.Lists.Strings;
import main.java.soupit.HilfsKlassen.Rezept;
import main.java.soupit.HilfsKlassen.Zutat;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static main.java.soupit.handlers.LaunchRequestHandler.REZEPT_ARRAY_LIST;

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

        String speechText, repromptText;
        boolean isAskResponse = false;

        // Check for favorite color and create output to user.
        if (ingredientSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String[] strinGredients = ingredientSlot.getValue().split("\\s");
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for(int i = 0;i<strinGredients.length; i++){
                ingredients[i] = new Zutat(strinGredients[i],"f");
            }
            
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap("Ingredient", ingredients));
            Rezept bestRecipe  = REZEPT_ARRAY_LIST.getBestFitting(ingredients);
            if (bestRecipe != null){
                speechText = "mit diesen Zutaten kannst du eine "+bestRecipe+ " kochen. ";
                speechText += "dafür brauchst du ";
                for(int i =0; i<bestRecipe.zumeng.length; i++) {
                    ZutatMengeEinheit zum = bestRecipe.zumeng[i];
                    if(i == bestRecipe.zumeng.length -1) speechText += " und ";
                    speechText += zum.mengeToString() + " ";
                    speechText += zum.einheitToString() + " ";
                    speechText += zum.zutatToString() + " <break time=\"1s\"/>";
                    if (i < bestRecipe.zumeng.length -2) speechText += ", ";
                    if(i == bestRecipe.zumeng.length -1) speechText += ". ";

                }
            }else{
                speechText = "Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ";
            }





            repromptText = Strings.REPROMPT;

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = Strings.REPROMPT;
            repromptText = Strings.REPROMPT;
            isAskResponse = true;
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("Zutatenliste", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }

}

