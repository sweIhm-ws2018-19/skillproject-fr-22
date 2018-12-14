package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.SessionAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NextStepIntent implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NextStepIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        if(SessionAttributes.steps.length > SessionAttributes.stepTracker+1) {
            speechText = SessionAttributes.steps[++SessionAttributes.stepTracker];
            if (SessionAttributes.stepTracker == SessionAttributes.steps.length-1) {
                speechText += "Ich hoffe die Suppe schmeckt und wünsche einen guten Appetit. Bis zum nächsten Mal.";
            }
        }else{
            speechText = "es gibt nichts mehr zu tun ausser die suppe zu essen!";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
