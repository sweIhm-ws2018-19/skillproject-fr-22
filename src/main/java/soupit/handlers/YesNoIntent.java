package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
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
        if (yesNoSlot != null) {

            if (SessionAttributes.programState.equals(Strings.SOUP_YES_NO_STATE)) {
                if (yesNoSlot.getValue().equalsIgnoreCase("ja")){
                    SessionAttributes.currentRecipe = SessionAttributes.recipeToDecideOn;
                    speechText = "Wie viele Portionen möchtest du kochen ?";
                }else{
                    speechText = "tut mir leid dass ich nicht helfen konnte.";
                }
            } else if (SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE)){
                speechText = "Super! Sobald du mit dem Kochen anfangen möchtest, sage: Rezept starten ";
            }else{
                speechText = "nagut";
            }

        } else speechText = "das habe ich leider nicht verstanden";



        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
