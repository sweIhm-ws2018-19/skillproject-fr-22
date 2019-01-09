package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;

import java.util.List;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class MoreRecipesIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MoreRecipesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
      StringBuilder speechText = new StringBuilder("du kannst ausserdem noch ");
      int index = SessionAttributes.matchingRecipesIndex;
        List list = SessionAttributes.matchingRecipes;
        int remainingRecipesCount = list.size() - index;
        if(remainingRecipesCount == 1){
            speechText.append(list.get(index));
            speechText.append(" kochen. möchtest du das tun?");
        }else {
            speechText.append("folgende Rezepte mit diesen Zutaten kochen: ");
            for (; index < list.size(); index++) {
                speechText.append(list.get(index));
                if(remainingRecipesCount > 1){
                    speechText.append(", ");
                }
                remainingRecipesCount--;
                if(remainingRecipesCount == 1){
                    speechText.append("und ");
                }
            }
            speechText.append(". Welche wählst du ? ");
        }
        PersistentAttributes.setLastSentence(speechText.toString(),input);
        return input.getResponseBuilder()
                .withSpeech(speechText.toString())
                .withShouldEndSession(false)
                .build();

    }
}
