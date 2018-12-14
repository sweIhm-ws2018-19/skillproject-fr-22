package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.SessionAttributes;

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
            speechText = "du willst "+numberSlot.getValue() +" Portionen kochen.";
        }else{
            speechText = "das habe ich leider nicht verstanden";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();

    }
}
