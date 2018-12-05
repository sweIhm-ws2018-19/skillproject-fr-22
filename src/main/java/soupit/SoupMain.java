package main.java.soupit;

import main.java.soupit.HilfsKlassen.*;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class SoupMain {


    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList();
    public static final String[] alleRezepte = {"kartoffelcremesuppe"};



    public static void main(String... args) {

        try {
            Object obj = new JSONParser().parse(new FileReader("src\\main\\java\\rezepte.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            for (String s : alleRezepte) {
                addRecipes(s, rezepte,zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strinGredients = {"kartoffeln"};
        Zutat[] ingredients = new Zutat[strinGredients.length];
        for(int i = 0;i<strinGredients.length; i++){
            ingredients[i] = new Zutat(strinGredients[i],"f");
        }

        String speechText;

        Rezept bestRecipe  = REZEPT_ARRAY_LIST.getBestFitting(ingredients);
        if (bestRecipe != null){
            speechText = "mit diesen Zutaten kannst du eine "+bestRecipe+ " kochen ";
            speechText += "dafür brauchst du ";
            for(ZutatMengeEinheit zum:bestRecipe.zumeng){
                speechText += zum.mengeToString() +" ";
                speechText += zum.einheitToString() +" ";
                speechText += zum.zutatToString() +" ";
            }
        }else{
            speechText = "Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ";
        }

        System.out.println(speechText);

    }

    private static void addRecipes(String rezeptname, Map rezepte, Map zutatenMitGeschlecht, Map einheitenMitGeschlecht) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String,Map>> it = zutaten.entrySet().iterator();
        int counter = 0;
        while(it.hasNext()){

            Map.Entry<String,Map> next = it.next();
            Map nextMap = next.getValue();
            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Zutat zutat = new Zutat(zutatString,(String) zutatenMitGeschlecht.get(zutatString));
            String einheitString = (String) nextMap.get("einheit");
            Einheit einheit = new Einheit(einheitString,(String) einheitenMitGeschlecht.get(einheitString));
            double menge = Double.parseDouble((String)(nextMap.get("menge")));

            zumeng[counter] = new ZutatMengeEinheit(zutat,menge,einheit);
            counter++;
        }
        REZEPT_ARRAY_LIST.add(new Rezept(rezeptname,zumeng));



    }



}
