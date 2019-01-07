package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
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
        SessionAttributes.stepTracker = PersistentAttributes.getStepCount(input);
        if(SessionAttributes.steps.length > SessionAttributes.stepTracker+1) {
            speechText = SessionAttributes.steps[++SessionAttributes.stepTracker];
            PersistentAttributes.setStepCount(input);
            if (SessionAttributes.stepTracker == SessionAttributes.steps.length-1) {

                speechText += Strings.getRandomFinish();
                PersistentAttributes.clear(input);
            }
        }else{
            speechText = "es gibt nichts mehr zu tun ausser die suppe zu essen!";
        }

        PersistentAttributes.setLastSentence(speechText,input);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
