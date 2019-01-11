package soupit;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import soupit.Lists.Strings;

import java.util.Map;


public class PersistentAttributes {


//    recipeName
//    stepCount
//    programState


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



    public static void setStepCount(HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("stepCount", String.valueOf(SessionAttributes.stepTracker));
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
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



    public static void setLastSentence(String lastSentence, HandlerInput input){
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("lastSentence", lastSentence);
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static String getLastSentence(HandlerInput input){
        return input.getAttributesManager().getPersistentAttributes().get("lastSentence").toString();
    }

    public static void setRecipeToDecideOn(HandlerInput input){
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("recipeToDecideOn", SessionAttributes.recipeToDecideOn.toString());
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static void setNumberOfServings(HandlerInput input){
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("numberOfServings",String.valueOf(SessionAttributes.numberOfServings));
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
    }

    public static void download(HandlerInput input){
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        if (persistentAttributes.get("stepCount") != null) {
            SessionAttributes.stepTracker = Integer.parseInt(persistentAttributes.get("stepCount").toString());
        }else{
            SessionAttributes.stepTracker = 0;
        }
        if(persistentAttributes.get("recipeName") != null){
            SessionAttributes.setCurrentRecipe(persistentAttributes.get("recipeName").toString());
        }else{
            SessionAttributes.currentRecipe = null;
        }
        if(persistentAttributes.get("programState") != null){
            SessionAttributes.programState = persistentAttributes.get("programState").toString();
        }else{
            SessionAttributes.programState = Strings.INITIAL_STATE;
        }
        if(persistentAttributes.get("recipeToDecideOn") != null){
            SessionAttributes.recipeToDecideOn = SessionAttributes.recipes.find(persistentAttributes.get("recipeToDecideOn").toString());
        }else{
            SessionAttributes.recipeToDecideOn = null;
        }
        if(persistentAttributes.get("numberOfServings") != null ){
            SessionAttributes.numberOfServings = Integer.parseInt(persistentAttributes.get("numberOfServings").toString());
        }else{
            SessionAttributes.numberOfServings = 0;
        }
    }

    public static void clear(HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        persistentAttributes.put("recipeName", null);
        persistentAttributes.put("stepCount",String.valueOf(0));
        persistentAttributes.put("programState",Strings.INITIAL_STATE);
        persistentAttributes.put("recipeToDecideOn",null);
        persistentAttributes.put("numberOfServings",String.valueOf(0));
        input.getAttributesManager().setPersistentAttributes(persistentAttributes);
        input.getAttributesManager().savePersistentAttributes();
        SessionAttributes.clear(false);
    }
}
