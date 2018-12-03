package main.java.soupit;

import main.java.soupit.HilfsKlassen.Rezept;
import main.java.soupit.HilfsKlassen.RezeptArrayList;
import main.java.soupit.HilfsKlassen.Rezepte.KartoffelcremeSuppe;
import main.java.soupit.HilfsKlassen.Zutat;
import main.java.soupit.HilfsKlassen.ZutatMengeEinheit;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class SoupMain {


    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList(new KartoffelcremeSuppe());


    public static void main(String... args) throws Exception{

        Object obj = new JSONParser().parse(new FileReader("C:/Users/jktitareva/Documents/soupit/skillproject-fr-22/data/rezepte.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;


        // getting firstName and lastName
        String firstName = (String) jo.get("hey");

        System.out.println(firstName);


        // getting phoneNumbers
        JSONArray ja = (JSONArray) jo.get("zutat");

        // iterating phoneNumbers
        Iterator itr2 = ja.iterator();

        while (itr2.hasNext())
        {
            Iterator itr1 = ((Map) itr2.next()).entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = (Map.Entry) itr1.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }






        String speechText;
        String[] strinGredients = {"kartoffeln"};
        Zutat[] ingredients = new Zutat[strinGredients.length];
        for (int i = 0; i < strinGredients.length; i++) {
            ingredients[i] = new Zutat(strinGredients[i], "f");
        }

        Rezept bestRecipe = REZEPT_ARRAY_LIST.getBestFitting(ingredients);
        if (bestRecipe != null) {
            speechText = "mit diesen Zutaten kannst du eine " + bestRecipe + " kochen ";
            speechText += "dafür brauchst du ";
            for (ZutatMengeEinheit zum : bestRecipe.zumeng) {
                speechText += zum.mengeToString() + " ";
                speechText += zum.einheitToString() + " ";
                speechText += zum.zutatToString() + " ";
            }
        } else {
            speechText = "Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten! ";
        }
        System.out.println(speechText);

    }
}
