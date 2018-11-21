package main.java.colorpicker.Rezepte;

public class KartoffelcremeSuppe extends Rezept {

    static final ZutatMenge Kartoffeln =  new ZutatMenge(ZutatenListe.Kartoffel.get(),new MengeEinheit(250,Einheiten.GRAMM));

    static final ZutatMenge zumeng[] = {Kartoffeln};



    public KartoffelcremeSuppe(){
        super("KartoffelcremeSuppe", Kartoffeln);
    }


}
