package main.java.colorpicker.Rezepte;

public class KartoffelcremeSuppe extends Rezept {

    static final ZutatMenge Kartoffeln =  new ZutatMenge(ZutatenListe.Kartoffel.get(),new MengeEinheit(250,Einheiten.GRAMM));





    public KartoffelcremeSuppe(){
        super("KartoffelcremeSuppe", Kartoffeln);
    }


}
