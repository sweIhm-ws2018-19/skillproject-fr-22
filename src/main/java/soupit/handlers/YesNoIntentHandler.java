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

public class YesNoIntentHandler implements RequestHandler {

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
        boolean jumpBack = false;

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
                    speechText = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_neutral_response_01'/>";
                    speechText +=((int) (Math.random()*2) )==0 ? "Super! Sobald du mit dem Kochen anfangen möchtest, sage: Rezept starten ": "Sehr gut! Wenn du bereit bist zum Kochen, sage: Rezept starten";
                    SessionAttributes.programState = Strings.STARTCOOKING_STATE;
                    PersistentAttributes.setProgramState(Strings.STARTCOOKING_STATE,input);
                }else{ // nein
                    speechText = "schade. soll ich die Zutaten auf eine Einkaufsliste schreiben, oder möchtest du eine andere suppe kochen ?";
                }
            }else  if(SessionAttributes.programState.equals(Strings.RESTART_YES_NO_STATE)){
                if(yesNoSlot.getValue().equalsIgnoreCase("ja")){
                    speechText = "Okay. welche Zutaten möchtest du für deine neue Suppe verwenden ?";
                    PersistentAttributes.clear(input);
                }else{ // nein
                    speechText = "Okay. lass uns da weitermachen, wo wir aufgehört haben. "+PersistentAttributes.getLastSentence(input);
                    jumpBack = true;
                }
            }else if (SessionAttributes.programState.equals(Strings.COOKING_STATE)) {           // danke herr berchtenbreiter
                if (SessionAttributes.steps.length > SessionAttributes.stepTracker + 1) {
                    speechText = SessionAttributes.steps[++SessionAttributes.stepTracker];
                    PersistentAttributes.setStepCount(input);
                    if (SessionAttributes.stepTracker == SessionAttributes.steps.length - 1) {
                        speechText += "<audio src='soundbank://soundlibrary/musical/amzn_sfx_bell_timer_01'/>";
                        speechText += Strings.getRandomFinish();
                        PersistentAttributes.clear(input);
                    }
                } else {
                    speechText = "es gibt nichts mehr zu tun ausser die suppe zu essen!";
                }
            }
            else if(SessionAttributes.programState.equals(Strings.CONTINUE_SOUP_YES_NO_STATE)) {
                if(yesNoSlot.getValue().equalsIgnoreCase("ja")){

                        speechText = "Okay. sage 'wiederholen' , wenn du den letzten Schritt noch einmal hören möchtest oder weiter, für den nächsten Schritt";
                        repeatStep = true;

                }else { // nein
                    speechText = "Okay. nenne mir die Zutaten, die du für deine neue Suppe verwenden willst";
                    PersistentAttributes.clear(input);
                }
            }else{
                speechText = "nagut";
            }

        } else speechText = "das habe ich leider nicht verstanden";

        if(repeatStep) PersistentAttributes.setLastSentence(SessionAttributes.steps[SessionAttributes.stepTracker],input);
        if(!jumpBack) PersistentAttributes.setLastSentence(speechText,input);

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
