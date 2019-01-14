package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NextStepIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NextStepIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;

        if(SessionAttributes.programState.equals(Strings.COOKING_STATE)) {

            if (SessionAttributes.steps.length > SessionAttributes.stepTracker + 1) {
                speechText = SessionAttributes.steps[++SessionAttributes.stepTracker];
                PersistentAttributes.setStepCount(input);
                if (SessionAttributes.stepTracker == SessionAttributes.steps.length - 1) {
                    speechText += "<audio src='soundbank://soundlibrary/musical/amzn_sfx_bell_timer_01'/>";
                    speechText += Strings.getRandomFinish();
                    PersistentAttributes.clear(input);
                    PersistentAttributes.setLastSentence(SessionAttributes.steps[SessionAttributes.steps.length-1],input);
                }
            } else {
                speechText = "es gibt nichts mehr zu tun ausser die suppe zu essen!";
            }
        }else{
            speechText = "der Kochvorgang hat noch nicht begonnen. Entschuldige falls ich dich falsch verstanden habe";

        }


        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
