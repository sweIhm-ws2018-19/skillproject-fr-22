package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class PauseIntent implements RequestHandler {

    public  boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PauseIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        if(PersistentAttributes.getProgramState(input).equals(Strings.STARTCOOKING_STATE)){
            speechText = "Kein Problem. Wenn du soweit bist, öffne soupit erneut.";
        }else{
            speechText = "Bis später!";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(true)
                .build();

    }
}
