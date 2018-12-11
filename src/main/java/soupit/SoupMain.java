package soupit;

import soupit.recipe.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class SoupMain {


    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList();
    public static final String[] alleRezepte = {"kartoffelcremesuppe", "möhrencremesuppe"};


    public static void main(String... args) {

        try {

            InputStream stream = SoupMain.class.getClassLoader().getResourceAsStream("data/rezepte.json");
            Object obj = new JSONParser().parse(new InputStreamReader(stream));
            JSONObject jsonObject = (JSONObject) obj;
            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            for (String s : alleRezepte) {
                addRecipes(s, rezepte, zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] strinGredients = {"möhren"};
        Zutat[] ingredients = new Zutat[strinGredients.length];
        for (int i = 0; i < strinGredients.length; i++) {
            ingredients[i] = new Zutat(strinGredients[i], "f");
        }

        StringBuilder speechText;

        NavigableMap<Integer, ArrayList<Rezept>> map = REZEPT_ARRAY_LIST.getFitting(ingredients);
        ArrayList<Rezept> list = treeMapToSortedList(map);
        if (!list.isEmpty()) {
            speechText = new StringBuilder("Mit diesen Zutaten kannst du ");
            int listSize = list.size();
            if (listSize == 1){
                speechText.append("eine ");
                speechText.append(list.get(0));
                speechText.append(" kochen. möchtest du das tun ?");
                ////PROGRAMSTATE COOK_SOUP_YES_NO
            }
            else if (listSize <= 3) {
                for (int i = 0; i < listSize; i++) {
                    speechText.append("eine ");
                    if (i < listSize - 2) {
                        speechText.append(list.get(i));
                        speechText.append(", ");
                    } else if (i == listSize - 2) {
                        speechText.append(list.get(i));
                        speechText.append(" oder ");
                    } else {
                        speechText.append(list.get(i));
                        speechText.append(" kochen. ");
                        speechText.append("Sag mir den Namen der Suppe die du kochen möchtest ");
                        ////PROGRAMSTATE GET_NAME_OF_SOUP
                    }
                }
            }else{
                speechText = new StringBuilder(" für diese Zutaten fallen mir ");
                speechText.append(listSize);
                speechText.append(" Rezepte ein. zu deinen Zutaten passen am besten die folgenden 3 Rezepte: ");
                speechText.append(list.get(0));
                speechText.append(", ");
                speechText.append(list.get(1));
                speechText.append("und ");
                speechText.append(list.get(2));
                speechText.append(" wenn dir eins davon gefällt sage mir den Namen des Rezeptes, oder weiter, wenn du mehr hören möchtest ");


//              Iterator<Map.Entry<Integer,ArrayList<Rezept>>> i = descendingMap.entrySet().iterator();
//              while (i.hasNext()){
//                  ArrayList<Rezept> list = i.next().getValue();
//                  for(Rezept r:list){
//
//                  }
//
//              }


//            speechText += "dafür brauchst du ";
//            for(int i =0; i<bestRecipe.zumeng.length; i++) {
//                ZutatMengeEinheit zum = bestRecipe.zumeng[i];
//                if(i == bestRecipe.zumeng.length -1) speechText += " und ";
//                speechText += zum.mengeToString() + " ";
//                speechText += zum.einheitToString() + " ";
//                speechText += zum.zutatToString() + " <break time=\"1s\"/>";
//                if (i < bestRecipe.zumeng.length -2) speechText += ", ";
//                if(i == bestRecipe.zumeng.length -1) speechText += ". ";

            }
        } else {
            speechText = new StringBuilder("Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ");
        }

        System.out.println(speechText);

    }

    private static void addRecipes(String rezeptname, Map rezepte, Map zutatenMitGeschlecht, Map einheitenMitGeschlecht) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String, Map>> it = zutaten.entrySet().iterator();                                //TODO foreachloop better
        int counter = 0;
        while (it.hasNext()) {

            Map.Entry<String, Map> next = it.next();
            Map nextMap = next.getValue();
            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Zutat zutat = new Zutat(zutatString, (String) zutatenMitGeschlecht.get(zutatString));
            String einheitString = (String) nextMap.get("einheit");
            Einheit einheit = new Einheit(einheitString, (String) einheitenMitGeschlecht.get(einheitString));
            double menge = Double.parseDouble((String) (nextMap.get("menge")));

            zumeng[counter] = new ZutatMengeEinheit(zutat, menge, einheit);
            counter++;
        }
        REZEPT_ARRAY_LIST.add(new Rezept(rezeptname, zumeng));


    }


    private static ArrayList<Rezept> treeMapToSortedList(Map<Integer, ArrayList<Rezept>> map) {                              //TODO Refactor this method to be in some other class
        ArrayList<Rezept> list = new ArrayList<>();
        for (ArrayList<Rezept> sublist : map.values()) {
            list.addAll(sublist);
        }
        return list;
    }
}