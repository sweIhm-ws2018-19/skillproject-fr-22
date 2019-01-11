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

   public static boolean setProgramState = true;
    public static boolean setLastSentence = true;
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

        Slot ingredientSlot = slots.get("Ingredient");
        Slot foodSlot = slots.get("Food");


        StringBuilder speechText;



        if(ingredientSlot.getValue() != null)   speechText = IngredientIntentManager.getSpeechResponse(input, ingredientSlot,this.getClass().getSimpleName());
        else if(foodSlot.getValue() != null)  speechText = IngredientIntentManager.getSpeechResponse(input, foodSlot,"IngredientIntentHandler");
        else speechText = new StringBuilder("tut mir leid, dass habe ich nicht verstanden, kannst du das wiederholen");

        if(setProgramState) {
            SessionAttributes.programState = Strings.INGREDIENT_NAMED_STATE;
            PersistentAttributes.setProgramState(Strings.INGREDIENT_NAMED_STATE,input);
        }
        if(setLastSentence) PersistentAttributes.setLastSentence(speechText.toString(),input);

        ResponseBuilder responseBuilder = input.getResponseBuilder();
        responseBuilder.withSpeech(speechText.toString())
                .withShouldEndSession(false);
        return responseBuilder.build();
    }



}