package soupit;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Slot;
import org.json.simple.JSONArray;
import soupit.Lists.Strings;
import soupit.recipe.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class SoupMain {

   static  Object tester2 = new Object();




    public static void main(String... args) {
        List<Rezept> list ;
        Object tester = new Object();


        try {

            InputStream stream = SoupMain.class.getClassLoader().getResourceAsStream("data/rezepte.json");
            Object obj = new JSONParser().parse(new InputStreamReader(stream,"UTF-8"));
            JSONObject jsonObject = (JSONObject) obj;



            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            Object [] alleRezepte =  ((Map) jsonObject.get("rezepte")).keySet().toArray();

            for (Object s : alleRezepte) {
                tester =s;
                if(s.toString().equals("kohlrabi kartoffelsuppe")){
                    int a = 0;
                }
                addRecipes(s.toString(), rezepte, zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
        } catch (Exception e) {
            System.out.println(tester);
            System.out.println(tester2);
            System.out.println(e.getMessage());
        }

        String speechText  = "";
        SessionAttributes.setCurrentRecipe("lasagnesuppe");
        SessionAttributes.currentRecipe.multiplyZumeng(5);
        ZutatMengeEinheit [] zumArray = SessionAttributes.getCurrentRecipeZumeng();
        for(int i =0; i<zumArray.length; i++) {
            ZutatMengeEinheit zum = zumArray[i];
            if (i == zumArray.length - 1) speechText += " und ";
            speechText += zum.mengeToString() + " ";
            speechText += zum.einheitToString() + " ";
            speechText += zum.zutatToString() + " <break time=\"1s\"/>";
            if (i < zumArray.length - 2) speechText += ", ";
            if (i == zumArray.length - 1) speechText += ". ";
        }

        System.out.println(speechText);
        System.out.println(getSpeechResponse());
    }

    private static void addRecipes(String rezeptname, Map rezepte, Map<String,Map<String,String>> alleZutaten, Map<String,Map<String,String>> einheiten) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        Map jsonsteps  = (Map) rezept.get("schritte");
        Object[] objectsteps = jsonsteps.values().toArray();
        String[] steps = new String[objectsteps.length];
        for(int i=0; i <objectsteps.length; i++){
            steps[i] = objectsteps[i].toString();
        }
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String, Map>> it = zutaten.entrySet().iterator();                                //TODO foreachloop better
        int counter = 0;
        while (it.hasNext()) {
            Map.Entry<String, Map> next = it.next();
            Map nextMap = next.getValue();
            tester2 = nextMap;

            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Object synonObject = alleZutaten.get(zutatString).get("synonyms");
            JSONArray jsonsynonyms =((JSONArray) synonObject);
            Object[] synonObjects = jsonsynonyms.toArray();
            ArrayList<String> synonyms = new ArrayList<>();
            for(Object o: synonObjects){
                synonyms.add(o.toString());
            }
//            for(int i= 0; i< synonyms.length; i++){
//                synonyms[i] = jsonsynonyms.toArray()[i].toString();
//            }

            Zutat zutat = new Zutat(zutatString, alleZutaten.get(zutatString).get("gender"),alleZutaten.get(zutatString).get("plural"),synonyms);
            String einheitString = (String) nextMap.get("einheit");
            Einheit einheit = new Einheit(einheitString,einheiten.get(einheitString).get("gender"),einheiten.get(einheitString).get("plural"));
            String mengeString = nextMap.get("menge").toString();
            double menge = mengeString.equals("null")? 0 : Double.parseDouble((String) (nextMap.get("menge")));
            if (menge == 0.3) menge = 1/3d;
            if (menge == 0.15) menge = 1/6d;

            zumeng[counter] = new ZutatMengeEinheit(zutat, menge, einheit);
            counter++;
        }
        SessionAttributes.recipes.add(new Rezept(rezeptname,steps, zumeng));


    }

    private static StringBuilder getSpeechResponse() {
        StringBuilder speechText;
        if (true) {
            // Store the user's favorite color in the Session and create response.
            String[] strinGredients = {"fisch"};
            Zutat[] ingredients = new Zutat[strinGredients.length];
            for (int i = 0; i < strinGredients.length; i++) {
                ingredients[i] = new Zutat(strinGredients[i], "gender","plural",new ArrayList<>());
            }



            NavigableMap<Integer, ArrayList<Rezept>> map = SessionAttributes.recipes.getFitting(ingredients);
            ArrayList<Rezept> listwithAll = treeMapToSortedList(map);
            List<Rezept> list;
            if (listwithAll.size() > 6) {
                list = listwithAll.subList(0,6);
            }else {
                list = listwithAll;
            }

            if (!list.isEmpty()) {
                speechText = new StringBuilder("ich kann dir anhand der genannten Zutaten ");
                int listSize = list.size();
                if (listSize == 1)speechText.append("ein");
                else speechText.append(listSize);
                speechText.append(" Rezept");
                if(listSize > 1) speechText.append("e");
                speechText.append(" vorschlagen: ");
                if (listSize == 1) {
                    speechText.append(list.get(0));
                    speechText.append(". Möchtest du diese Suppe kochen?");
                    SessionAttributes.recipeToDecideOn= list.get(0);
                    SessionAttributes.programState = Strings.SOUP_YES_NO_STATE;

                }
                else if (listSize <= 3) {
                    for (int i = 0; i < listSize; i++) {
                        speechText.append(list.get(i));
                        if (i < listSize - 2) {
                            speechText.append(", ");
                        } else if (i == listSize - 2) {
                            speechText.append(", oder ");
                        } else {
                            speechText.append(". Welche suppe wählst du?");
                        }
                    }
                } else {
                    speechText.append(list.get(0));
                    speechText.append(", ");
                    speechText.append(list.get(1));
                    speechText.append(", ");
                    speechText.append(list.get(2));
                    speechText.append(" und ");
                    int remainingRecipesCount = listSize-3;
                    speechText.append(remainingRecipesCount);
                    speechText.append(" weitere");
                    if (remainingRecipesCount == 1) speechText.append("s");
                    speechText.append(". ");
                    speechText.append("wähle eine Suppe oder sage: weitere anhören ");
                    SessionAttributes.matchingRecipes = list;
                    SessionAttributes.matchingRecipesIndex = 3;



                }
            } else {
                speechText =  new StringBuilder("Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ");
            }





        }else{
            speechText = new StringBuilder("tut mir leid, das habe ich nicht verstanden. kannst du das wiederholen ?");
        } return speechText;
    }


    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {                              //TODO Refactor this method to be in some other class
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }


}