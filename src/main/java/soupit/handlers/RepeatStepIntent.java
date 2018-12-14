package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.SessionAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RepeatStepIntent implements RequestHandler {
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartCookingIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = SessionAttributes.steps[SessionAttributes.stepTracker];


        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
