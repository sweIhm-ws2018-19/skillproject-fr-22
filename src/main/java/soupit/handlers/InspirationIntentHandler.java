package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.Lists.Strings;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class InspirationIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("InspirationIntent"));
    }


    public Optional<Response> handle(HandlerInput input) {
        String speechText = Strings.geRandomInspiration();

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}