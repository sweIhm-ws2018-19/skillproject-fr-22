package soupit;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.HashMap;
import java.util.Map;


public class PersistentAttributes {


//    private static String recipeName;
//    private Rezept recipe;
//    private static int stepCount;
//    private static String programState;

    public static String getRecipeName(HandlerInput input) {
        String recipeName = input.getAttributesManager().getPersistentAttributes().get("recipeName").toString();
        SessionAttributes.setCurrentRecipe(recipeName);
        return recipeName;
    }

    public static boolean setRecipeName(String recipeName, HandlerInput input) {
        if (SessionAttributes.setCurrentRecipe(recipeName)) {
            Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
            persistentAttributes.put("recipeName", recipeName);
            input.getAttributesManager().setPersistentAttributes(persistentAttributes);
            input.getAttributesManager().savePersistentAttributes();
            return true;
        } else {
            return false;
        }
    }


    public static int getStepCount(HandlerInput input) {
        return Integer.parseInt(input.getAttributesManager().getPersistentAttributes().get("stepCount").toString());
    }

    public static void setStepCount(int stepCount, HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("stepCount", String.valueOf(stepCount));
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
//        SessionAttributes.stepTracker = stepCount;
    }

    public static String getProgramState(HandlerInput input) {
        return input.getAttributesManager().getPersistentAttributes().get("programState").toString();
    }

    public static void setProgramState(String programState, HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("programState", programState);
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static void clear(HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        //persistentAttributes.put("recipeName", null);
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static void setLastSentence(String lastSentence, HandlerInput input){
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("lastSentence", lastSentence);
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static String getLastSentence(HandlerInput input){
        return input.getAttributesManager().getPersistentAttributes().get("lastSentence").toString();
    }
}
