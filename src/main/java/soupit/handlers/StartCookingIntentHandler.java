package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.recipe.IngredientIntentManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class StartCookingIntentHandler implements RequestHandler {



    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartCookingIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {


        String speechText;
        if( SessionAttributes.programState.equals(Strings.STARTCOOKING_STATE) ) {
            speechText = "Alles klar. Lass uns mit der Zubereitung der " + PersistentAttributes.getRecipeName(input) + " beginnen. ";
            speechText += "du kannst nach jedem Zubereitungsschritt <emphasis level=\"moderate\">weiter</emphasis> oder <emphasis level=\"moderate\">schritt wiederholen</emphasis> sagen. ";
            speechText+=SessionAttributes.currentRecipe.getSteps()[0];
            SessionAttributes.stepTracker = 0;
            SessionAttributes.programState = Strings.COOKING_STATE;
            PersistentAttributes.setProgramState(Strings.COOKING_STATE,input);
            PersistentAttributes.setLastSentence(SessionAttributes.steps[0],input);
        }
        else  {
            speechText = "das habe ich leider nicht verstanden, kannst du das wiederholen? ";

        }





        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
