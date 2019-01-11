package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;


import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NameRecipeIntentHandler implements RequestHandler {

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
            if(PersistentAttributes.setRecipeName(recipeSlot.getValue(),input)){
                speechText = "Wie viele Portionen möchtest du kochen?";
                SessionAttributes.programState = Strings.RECIPE_CHOSEN_STATE;
                PersistentAttributes.setProgramState(Strings.RECIPE_CHOSEN_STATE,input);
            }else speechText = "entschuldigung, dieses rezept habe ich nicht gefunden, kannst du es noch einmal wiederholen? ";
        }else{
            speechText = "das habe ich leider nicht verstanden";
        }
        PersistentAttributes.setLastSentence("wie viele Portionen der "+SessionAttributes.currentRecipe+" möchtest du kochen?",input);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
