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

public class YesNoIntent implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("YesNoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot yesNoSlot = slots.get("YesOrNo");
        String speechText;

        boolean repeatStep = false;

        if (yesNoSlot != null) {



            if (SessionAttributes.programState.equals(Strings.SOUP_YES_NO_STATE)) {
                if (yesNoSlot.getValue().equalsIgnoreCase("ja")){
                    SessionAttributes.currentRecipe = SessionAttributes.recipeToDecideOn;
                    PersistentAttributes.setRecipeName(SessionAttributes.recipeToDecideOn.toString(),input);
                    speechText = "Wie viele Portionen möchtest du kochen ?";
                }else{ // nein
                    speechText = "tut mir leid dass ich nicht helfen konnte.";
                }
            } else if (SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE)){
                if(yesNoSlot.getValue().equalsIgnoreCase("ja")) {
                    speechText =((int) (Math.random()*2) )==0 ? "Super! Sobald du mit dem Kochen anfangen möchtest, sage: Rezept starten ": "Wenn du bereit bist zum Kochen, sage: Rezept starten";
                    PersistentAttributes.setProgramState(Strings.STARTCOOKING_STATE,input);
                }else{ // nein
                    speechText = "schade. soll ich die Zutaten auf eine Einkaufsliste schreiben, oder möchtest du eine andere suppe kochen ?";
                }
            }else {
                if(yesNoSlot.getValue().equalsIgnoreCase("ja")){
                    if(SessionAttributes.programState.equals(Strings.COOKING_STATE)){
                        speechText = "Okay. sage 'wiederholen' , wenn du den letzten Schritt noch einmal hören möchtest oder weiter, für den nächsten";
                        repeatStep = true;
                    }else{
                        speechText = SessionAttributes.programState;
                    }
                }else { // nein
                    speechText = "Okay. nenne mir die Zutaten, die du für deine neue Suppe verwenden willst";
                    PersistentAttributes.clear(input);
                }
            }

        } else speechText = "das habe ich leider nicht verstanden";

        if(repeatStep) PersistentAttributes.setLastSentence(SessionAttributes.steps[SessionAttributes.stepTracker],input);
        else PersistentAttributes.setLastSentence(speechText,input);

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
