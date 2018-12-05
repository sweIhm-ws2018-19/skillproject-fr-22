package main.java.soupit;

import main.java.soupit.HilfsKlassen.*;
import main.java.soupit.HilfsKlassen.Rezepte.KartoffelcremeSuppe;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class SoupMain {


    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList();
    public static final String[] alleRezepte = {"kartoffelcremesuppe"};


    public static void main(String... args) {


        try {
            Object obj = new JSONParser().parse(new FileReader("data\\rezepte.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Map> rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            for (String s : alleRezepte) {
                addRecipes(s, rezepte,zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int a =0;
    }


    private static void addRecipes(String rezeptname, Map rezepte, Map zutatenMitGeschlecht, Map einheitenMitGeschlecht) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String,Map>> it = zutaten.entrySet().iterator();
        int counter = 0;
        while(it.hasNext()){

            Map.Entry<String,Map> next = it.next();
            Map<String,String> nextval = next.getValue();
            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Zutat zutat = new Zutat(zutatString,(String) zutatenMitGeschlecht.get(zutatString));
            String einheitString =  nextval.get("einheit");
            Einheit einheit = new Einheit(einheitString,(String) einheitenMitGeschlecht.get(einheitString));
            double menge = Double.parseDouble(nextval.get("menge"));

            zumeng[counter] = new ZutatMengeEinheit(zutat,menge,einheit);
            counter++;
        }
        REZEPT_ARRAY_LIST.add(new Rezept(rezeptname,zumeng));


//        Map<String, String> karcremezutatenkartoffel = (Map<String, String>) zutaten.get("kartoffel");
//        double karcremezutatenkartoffelmenge = Double.parseDouble(karcremezutatenkartoffel.get("menge"));


    }
}
