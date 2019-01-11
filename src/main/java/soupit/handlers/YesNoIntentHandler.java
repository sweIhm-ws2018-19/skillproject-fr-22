package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import soupit.Lists.Strings;
import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.recipe.Rezept;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class YesNoIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("YesNoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot yesNoSlot = slots.get("YesOrNo");
        String speechText;

        boolean repeatStep = false;
        boolean jumpBack = false;
        boolean shouldEndSession = false;

        if (yesNoSlot != null) {


            if (SessionAttributes.programState.equals(Strings.SOUP_YES_NO_STATE)) {
                if (yesNoSlot.getValue().equalsIgnoreCase("ja")) {
                    SessionAttributes.currentRecipe = SessionAttributes.recipeToDecideOn;
                    PersistentAttributes.setRecipeName(SessionAttributes.recipeToDecideOn.toString(), input);
                    SessionAttributes.programState = Strings.RECIPE_CHOSEN_STATE;
                    PersistentAttributes.setProgramState(Strings.RECIPE_CHOSEN_STATE, input);
                    PersistentAttributes.setLastSentence(" Wie viele Portionen der" + SessionAttributes.recipeToDecideOn + " möchtest du kochen ?", input);
                    jumpBack = true;
                    speechText = "Wie viele Portionen möchtest du kochen ?";
                } else { // nein
                    speechText = "tut mir leid dass ich nicht helfen konnte.";
                }
            } else if (SessionAttributes.programState.equals(Strings.INGREDIENTSAVAILIABLE)) {
                if (yesNoSlot.getValue().equalsIgnoreCase("ja")) {
                    speechText = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_neutral_response_01'/>";
                    speechText += ((int) (Math.random() * 2)) == 0 ? "Super! Sobald du mit dem Kochen anfangen möchtest, sage: Rezept starten " : "Sehr gut! Wenn du bereit bist zum Kochen, sage: Rezept starten";
                    SessionAttributes.programState = Strings.STARTCOOKING_STATE;
                    PersistentAttributes.setProgramState(Strings.STARTCOOKING_STATE, input);
                } else { // nein
                    speechText = "Schade, Möchtest du stattdessen ein anderes Rezept auswählen?";
                    SessionAttributes.programState = Strings.OTHER_SOUP_YES_NO_STATE;

                }
            } else if (SessionAttributes.programState.equals(Strings.OTHER_SOUP_YES_NO_STATE)) {
                if (yesNoSlot.getValue().equalsIgnoreCase("ja")) {
                    if(SessionAttributes.matchingRecipes != null) {
                        SessionAttributes.matchingRecipes.remove(SessionAttributes.currentRecipe);
                        List<Rezept> list = SessionAttributes.matchingRecipes;

                        if (!SessionAttributes.matchingRecipes.isEmpty()) {                                      //////// ich weiss dass es unschön ist aber ich hatte keine zeit mehr für refactoring /// gilt für so ziemlich jede klasse
                            speechText = "ich kann dir zu den genannten zutaten ausserdem ";
                            int listSize = list.size();
                            if (listSize == 1) speechText += "<emphasis level=\"strong\">ein</emphasis>";
                            else speechText += listSize;
                            speechText += " Rezept";
                            if (listSize > 1) speechText += "e";
                            speechText += " vorschlagen: ";
                            if (listSize == 1) {
                                speechText += list.get(0);
                                speechText += ". Möchtest du diese Suppe kochen?";
                                SessionAttributes.recipeToDecideOn = list.get(0);
                                SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;
                                PersistentAttributes.setProgramState(Strings.SOUP_YES_NO_STATE, input);
                                PersistentAttributes.setRecipeToDecideOn(input);
                            } else if (listSize <= 3) {
                                for (int i = 0; i < listSize; i++) {
                                    speechText += list.get(i);
                                    if (i < listSize - 2) {
                                        speechText += ", ";
                                    } else if (i == listSize - 2) {
                                        speechText += ", oder ";
                                    } else {
                                        speechText += ". Welche suppe wählst du?";
                                    }
                                }
                            } else {
                                speechText += list.get(0);
                                speechText += ", ";
                                speechText += list.get(1);
                                speechText += ", ";
                                speechText += list.get(2);
                                speechText += " und ";
                                int remainingRecipesCount = listSize - 3;
                                if (remainingRecipesCount == 1) speechText += "ein weiteres";
                                else {
                                    speechText += remainingRecipesCount;
                                    speechText += " weitere";
                                }
                                speechText += ". ";
                                speechText += "wähle eine Suppe oder sage: weitere anhören ";
                                SessionAttributes.matchingRecipes = list;
                                SessionAttributes.matchingRecipesIndex = 3;

                            }
                        } else {
                            speechText = "zu deinen Zutaten habe ich leider keine anderen rezepte gefunden,  nenne mir doch ein paar andere, oder lass dich von mir inspirieren!";
                            PersistentAttributes.clear(input);
                        }
                    }else{
                        speechText = "zu deinen Zutaten habe ich leider keine anderen rezepte gefunden,  nenne mir doch ein paar andere, oder lass dich von mir inspirieren!";
                        PersistentAttributes.clear(input);
                    }
                 }else{ // nein
                    speechText = "Okay. Bis zum nächsten mal.";
                    jumpBack = true;
                    shouldEndSession = true;
                }
            } else if (SessionAttributes.programState.equals(Strings.RESTART_YES_NO_STATE)) {
                    if (yesNoSlot.getValue().equalsIgnoreCase("ja")) {
                        speechText = "Okay. welche Zutaten möchtest du für deine neue Suppe verwenden ?";
                        PersistentAttributes.clear(input);
                    } else { // nein
                        speechText = "Okay. lass uns da weitermachen, wo wir aufgehört haben. " + PersistentAttributes.getLastSentence(input);
                        jumpBack = true;
                    }
                } else if (SessionAttributes.programState.equals(Strings.COOKING_STATE)) {         //weil manche leute seltsam auf alexa antworten
                    if (SessionAttributes.steps.length > SessionAttributes.stepTracker + 1) {
                        speechText = SessionAttributes.steps[++SessionAttributes.stepTracker];
                        PersistentAttributes.setStepCount(input);
                        if (SessionAttributes.stepTracker == SessionAttributes.steps.length - 1) {
                            speechText += "<audio src='soundbank://soundlibrary/musical/amzn_sfx_bell_timer_01'/>";
                            speechText += Strings.getRandomFinish();
                            PersistentAttributes.clear(input);
                        }
                    } else {
                        speechText = "es gibt nichts mehr zu tun ausser die suppe zu essen!";
                    }
                } else if (SessionAttributes.programState.equals(Strings.CONTINUE_SOUP_YES_NO_STATE)) {
                    if (yesNoSlot.getValue().equalsIgnoreCase("ja")) {

                        speechText = "Okay. sage 'wiederholen' , wenn du den letzten Schritt noch einmal hören möchtest oder weiter, für den nächsten Schritt";
                        repeatStep = true;

                    } else { // nein
                        speechText = "Okay. nenne mir die Zutaten, die du für deine neue Suppe verwenden willst";
                        PersistentAttributes.clear(input);
                    }
                } else {
                    speechText = "nagut";
                }

            } else speechText = "das habe ich leider nicht verstanden";

            if (repeatStep) PersistentAttributes.setLastSentence(SessionAttributes.steps[SessionAttributes.stepTracker], input);
            if (!jumpBack) PersistentAttributes.setLastSentence(speechText, input);

            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(shouldEndSession)
                    .build();

        }
    }
