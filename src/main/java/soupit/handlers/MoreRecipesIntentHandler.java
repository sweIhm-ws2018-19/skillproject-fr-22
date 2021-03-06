package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
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
        if(list == null){
            speechText = new StringBuilder("ich habe wohl vergessen, was die anderen Rezepte waren. Meine Entwickler werden sich bald um mein erinnerungsvermögen kümmern");
        }else {
            int remainingRecipesCount = list.size() - index;
            if (remainingRecipesCount == 1) {
                speechText.append(list.get(index));
                speechText.append(" kochen. möchtest du das tun?");
                SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;
                PersistentAttributes.setProgramState(Strings.SOUP_YES_NO_STATE,input);
            } else {
                speechText.append("folgende Rezepte mit diesen Zutaten kochen: ");
                for (; index < list.size(); index++) {
                    speechText.append(list.get(index));
                    if (remainingRecipesCount > 1) {
                        speechText.append(", ");
                    }
                    remainingRecipesCount--;
                    if (remainingRecipesCount == 1) {
                        speechText.append("und ");
                    }
                }
                speechText.append(". Welche wählst du ? ");
            }
            PersistentAttributes.setLastSentence(speechText.toString(), input);
        }
        return input.getResponseBuilder()
                .withSpeech(speechText.toString())
                .withShouldEndSession(false)
                .build();

    }
}
