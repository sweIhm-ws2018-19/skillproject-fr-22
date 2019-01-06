package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RepeatIntent implements RequestHandler {
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RepeatIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = PersistentAttributes.getLastSentence(input);


        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
