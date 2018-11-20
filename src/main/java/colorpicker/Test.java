package main.java.colorpicker;

import main.java.colorpicker.HilfsKlassen.Rezepte.KartoffelcremeSuppe;
import main.java.colorpicker.HilfsKlassen.Rezept;
import main.java.colorpicker.HilfsKlassen.RezeptArrayList;
import main.java.colorpicker.HilfsKlassen.Zutat;
import main.java.colorpicker.HilfsKlassen.ZutatMengeEinheit;

import javax.sound.midi.Soundbank;


public class Test {
    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList(new KartoffelcremeSuppe());


    public static void main(String... args) {
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
