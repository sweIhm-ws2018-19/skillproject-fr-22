package main.java.colorpicker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;


public class TestIntentHandler implements RequestHandler {
    public static final String SICKNESS_KEY = "Sickness";
    public static final String SICKNESS_SLOT = "Sickness";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TestIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from the list of slots.
        Slot sicknessSlot = slots.get(SICKNESS_SLOT);

        String speechText, repromptText;
        boolean isAskResponse = false;

        // Check for favorite color and create output to user.
        if (sicknessSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String sickness = sicknessSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(SICKNESS_KEY, sickness));
            boolean has = (Math.random() < 0.5);
            if(has)
            speechText = "Ich konnte feststellen, dass du "+sickness+ " hast";
            else
            speechText = "Ich konnte feststellen, dass du "+sickness+ " nicht hast";



            repromptText =
                    "Frage nach meiner Lieblingsfarbe.";

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "Ich kenne Deine Lieblingsfarbe nicht. Bitte versuche es noch einmal.";
            repromptText =
                    "Ich weiss nicht welches Deine Lieblingsfarbe ist. Sag mir Deine Lieblingsfarbe. Sage zum Beispiel: ich mag blau.";
            isAskResponse = true;
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("SicknessChecker", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }

}
