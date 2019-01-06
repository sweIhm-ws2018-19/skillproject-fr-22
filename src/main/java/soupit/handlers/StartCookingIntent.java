package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class StartCookingIntent implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartCookingIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
       String speechText = "Alles klar. Lass uns mit der Zubereitung der " + PersistentAttributes.getRecipeName(input)+" beginnen. ";
       speechText += "du kannst nach jedem Zubereitungsschritt <emphasis level=\"moderate\">weiter</emphasis> oder <emphasis level=\"moderate\">schritt wiederholen</emphasis> sagen. ";
//        try {
//                InputStream stream = getClass().getClassLoader().getResourceAsStream("data/rezepte.json");
//                Object obj = new JSONParser().parse(new InputStreamReader(stream));
//                JSONObject jsonObject = (JSONObject) obj;
//                Map recipes =  (Map) jsonObject.get("rezepte");
//                Map recipe = (Map) recipes.get(SessionAttributes.currentRecipe.toString());
//                Map jsonsteps  = (Map) recipe.get("schritte");
//                Object[] objectsteps = jsonsteps.values().toArray();
//                String[] steps = new String[objectsteps.length];
//                for(int i=0; i <objectsteps.length; i++){
//                    steps[i] = objectsteps[i].toString();
//                }
//            speechText += steps[0];
//            SessionAttributes.steps = steps;
//            SessionAttributes.stepTracker = 0;
//
//            } catch (Exception e) {
//                speechText = e.getMessage();
//            }
        speechText+=SessionAttributes.currentRecipe.getSteps()[0];
        PersistentAttributes.setStepCount(0,input);

        PersistentAttributes.setLastSentence(speechText,input);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
