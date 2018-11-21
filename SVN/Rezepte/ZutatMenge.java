package main.java.colorpicker.Rezepte;

import java.util.Map;

public class ZutatMenge {
    Zutat zutat;
    MengeEinheit me;

    public ZutatMenge(Zutat zutat,MengeEinheit me){
        this.zutat = zutat;
        this.me = me;
    }

    public Zutat getZutat(){
        return zutat;
    }

    public MengeEinheit getMe() {
        return me;
    }
}
