package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.recipe.Rezept;
import soupit.recipe.Zutat;
import soupit.recipe.ZutatMengeEinheit;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HowMuchIntentHandler implements RequestHandler {

    public  boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HowMuchIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot ingredientSlot = slots.get("Ingredient");
        String speechText ="";
        if(SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE) || SessionAttributes.programState.equals(Strings.COOKING_STATE)){
            if(ingredientSlot.getValue() != null){
                boolean found = false;
                Zutat zutat = new Zutat(ingredientSlot.getValue(),"gender","plural",new ArrayList<>());
                SessionAttributes.currentRecipe.multiplyZumeng(SessionAttributes.numberOfServings);
                ZutatMengeEinheit [] currentRecipeZumeng = SessionAttributes.getCurrentRecipeZumeng();
                for(ZutatMengeEinheit zumeng:currentRecipeZumeng){
                    if(zumeng.getZutat().equals(zutat)){
                        speechText = zumeng.mengeToString()+" "+zumeng.einheitToString()+" "+zumeng.zutatToString();
                        found = true;
                        break;
                    }
                }
                if(!found){
                    speechText = "ich konnte die Zutat "+zutat+" in dem aktuellen Rezept nicht finden, wasche mir am besten meine Ohren unter fliessendem wasser";
                }

             if(SessionAttributes.numberOfServings != 0)  SessionAttributes.currentRecipe.multiplyZumeng(1/(double)(SessionAttributes.numberOfServings)); // sollte eigentlich so oder so nicht passieren aber man weiss ja nie
            }else{
                speechText = "tut mir leid, das habe ich nicht verstanden, kannst du das wiederholen?";
            }
            if(SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE)) speechText += ". wenn du alle zutaten hast sage ja, wenn du nochmal die Zutaten hören willst, sage wiederholen";
           else  if(SessionAttributes.programState.equals(Strings.COOKING_STATE)) speechText += ". möchtest du den letzen schritt wiederholen, oder den nächsten hören ?";

        }else{
            speechText = "tut mir leid, das habe ich nicht verstanden, kannst du das wiederholen?";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
