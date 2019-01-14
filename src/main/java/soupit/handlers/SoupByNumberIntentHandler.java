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

public class SoupByNumberIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SoupByNumberIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot ingredientSlot = slots.get("OrdinalNumbers");

        String speechText;
        if(SessionAttributes.programState.equals(Strings.INGREDIENT_NAMED_STATE) || SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE)){
                String ordinalNumber = ingredientSlot.getValue();
                int number;
                if(ordinalNumber.equalsIgnoreCase("erste")) number = 1;
                else if(ordinalNumber.equalsIgnoreCase("zweite")) number = 2;
                else if(ordinalNumber.equalsIgnoreCase("dritte")) number = 3;
                else if(ordinalNumber.equalsIgnoreCase("vierte")) number = 4;
                else if(ordinalNumber.equalsIgnoreCase("fünfte"))number = 5;
                else number = 6;
                number --;
                if(SessionAttributes.matchingRecipes.get(number) != null ){
                    speechText = "wie viele Portionen möchtest du kochen?";
                    SessionAttributes.currentRecipe = SessionAttributes.matchingRecipes.get(number);
                    PersistentAttributes.setRecipeName(SessionAttributes.currentRecipe.toString(),input);
                    SessionAttributes.programState = Strings.RECIPE_CHOSEN_STATE;
                    PersistentAttributes.setProgramState(Strings.RECIPE_CHOSEN_STATE,input);
                }else{
                    speechText = "es gibt nur "+SessionAttributes.matchingRecipes.size()+" passende Rezepte zu deinen Zutaten";
                }
        }else{
            speechText = "das habe ich leider nicht verstanden, kannst du das wiederholen?";

        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }

}
