package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
import soupit.SoupITStreamHandler;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class YesNoIntent implements RequestHandler{

        public boolean canHandle(HandlerInput input) {
            return input.matches(intentName("YesNoIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {
            Request request = input.getRequestEnvelope().getRequest();
            IntentRequest intentRequest = (IntentRequest) request;
            Intent intent = intentRequest.getIntent();
            Map<String, Slot> slots = intent.getSlots();
            Slot yesNoSlot = slots.get("YesNo");
            String speechText;
            if(SoupITStreamHandler.ProgramState.equals(Strings.SOUP_YES_NO_STATE)){
                //Cooker.cook(MomentarySoup)
            }else if(SoupITStreamHandler.ProgramState.equals(Strings.INGREDIENTSAVAILIABLE)){
                //do something esle
            }
            if(yesNoSlot != null){
                speechText = "du hast dich für "+ SoupITStreamHandler.recipeToDecideOn +" entschieden";
            }else{
                speechText = "das habe ich leider nicht verstanden";
            }

            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(false)
                    .build();

        }
}
