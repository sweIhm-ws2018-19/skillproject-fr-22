package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.SessionAttributes;


import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NameRecipeIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NameRecipeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot recipeSlot = slots.get("Recipe");
        String speechText;
        if(recipeSlot != null){
            if(SessionAttributes.setCurrentRecipe(recipeSlot.getValue())){
                speechText = "du hast dich für "+recipeSlot.getValue() +" entschieden";
            }else speechText = "entschuldigung, dieses rezept habe ich nicht gefunden, kannst du es noch einmal wiederholen? ";
        }else{
            speechText = "das habe ich leider nicht verstanden";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
