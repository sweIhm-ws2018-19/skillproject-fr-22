package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import soupit.Lists.Strings;
import soupit.SessionAttributes;
import soupit.SoupMain;
import soupit.recipe.Zutat;
import soupit.recipe.ZutatMengeEinheit;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NumberOfServingsIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NumberOfServingsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot numberSlot = slots.get("Number");
        String speechText;
        if(numberSlot != null){
            int numberOfServings = Integer.parseInt(numberSlot.getValue());
            String servingNumber = numberOfServings == 1 ? "eine Portion" : numberOfServings+" Portionen";
            speechText = "Für eine "+SessionAttributes.currentRecipe+" für " + servingNumber + " benötigst du einige Zutaten. Lege dir folgendes bereit. ";
//            try {
//                InputStream stream = SoupMain.class.getClassLoader().getResourceAsStream("data/rezepte.json");
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
//
//            } catch (Exception e) {
//                e.getMessage();
//            }

            ZutatMengeEinheit [] zumArray = SessionAttributes.getCurrentRecipeZumeng();
            for(int i =0; i<zumArray.length; i++) {
                ZutatMengeEinheit zum = zumArray[i];
                if (i == zumArray.length - 1) speechText += " und ";
                speechText += zum.mengeToString() + " ";
                speechText += zum.einheitToString() + " ";
                speechText += zum.zutatToString() + " <break time=\"1s\"/>";
                if (i < zumArray.length - 2) speechText += ", ";
                if (i == zumArray.length - 1) speechText += ". ";
            }
            speechText += "Hast du alle Zutaten vorrätig? ";
            SessionAttributes.programState = Strings.INGREDIENTSAVAILIABLE;
        }else{
            speechText = "das habe ich leider nicht verstanden";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
