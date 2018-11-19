package main.java.colorpicker.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import main.java.colorpicker.Lists.Strings;
import main.java.colorpicker.Rezepte.Rezept;
import main.java.colorpicker.Rezepte.Zutat;
import main.java.colorpicker.Rezepte.ZutatenListe;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static main.java.colorpicker.handlers.LaunchRequestHandler.rezepte;

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
            Zutat ingredient =  new Zutat(ingredientSlot.getValue());
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap("Ingredient", ingredient));
            Rezept bestRecipe  = rezepte.getBestFitting(ingredient);
            if (bestRecipe != null){
                speechText = "mit diesen Zutaten kannst du eine "+bestRecipe+ " kochen";
            }else{
                speechText = "Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten!";
            }



            repromptText = Strings.REPROMPT;

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = Strings.REPROMPT;
            repromptText = Strings.REPROMPT;
            isAskResponse = true;
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("ColorSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }

}

