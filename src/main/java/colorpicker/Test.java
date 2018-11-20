package main.java.colorpicker;

import main.java.colorpicker.Rezepte.KartoffelcremeSuppe;
import main.java.colorpicker.Rezepte.Rezept;
import main.java.colorpicker.Rezepte.Rezepte;
import main.java.colorpicker.Rezepte.Zutat;




public class Test {
    public static final Rezepte rezepte = new Rezepte(new KartoffelcremeSuppe());


    public static void main(String... args) {
        String speechText;
        String[] strinGredients = {"kartoffeln"};
        Zutat[] ingredients = new Zutat[strinGredients.length];
        for (int i = 0; i < strinGredients.length; i++) {
            ingredients[i] = new Zutat(strinGredients[i]);
        }

        Rezept bestRecipe = rezepte.getBestFitting(ingredients);
        if (bestRecipe != null) {
            speechText = "mit diesen Zutaten kannst du eine " + bestRecipe + " kochen";
        } else {
            speechText = "Ich habe leider kein Rezept mit diesen Zutaten auf Lager. ich werde daran arbeiten!";
        }
        System.out.println(speechText);
    }
}
