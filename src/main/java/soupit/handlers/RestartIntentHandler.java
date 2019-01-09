package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RestartIntentHandler implements RequestHandler {

    public  boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RestartIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {

        String speechText = "bist du dir sicher, dass du von vorne beginnen möchtest?";
        SessionAttributes.programState = Strings.RESTART_YES_NO_STATE;
        PersistentAttributes.setProgramState(Strings.RESTART_YES_NO_STATE,input);



        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
