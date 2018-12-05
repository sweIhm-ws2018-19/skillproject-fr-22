package main.java.soupit.HilfsKlassen.Rezepte;

import main.java.soupit.HilfsKlassen.EinheitenListe;
import main.java.soupit.HilfsKlassen.Rezept;
import main.java.soupit.HilfsKlassen.ZutatMengeEinheit;
import main.java.soupit.HilfsKlassen.ZutatenListe;

public class KartoffelcremeSuppe extends Rezept {

    static final ZutatMengeEinheit kartoffeln =  new ZutatMengeEinheit(ZutatenListe.KARTOFFEL.get(),125, EinheitenListe.GRAMM.get());
    static final ZutatMengeEinheit zwiebeln   =  new ZutatMengeEinheit(ZutatenListe.ZWIEBEL.get(),1/4d, EinheitenListe.NULL.get());
    static final ZutatMengeEinheit gemüseBrühe = new ZutatMengeEinheit(ZutatenListe.GEMÜSEBRÜHE.get(),110, EinheitenListe.MILLILITER.get());
    static final ZutatMengeEinheit öl = new ZutatMengeEinheit(ZutatenListe.ÖL.get(),1/2d, EinheitenListe.ESSLÖFFEL.get());
    static final ZutatMengeEinheit sahne = new ZutatMengeEinheit(ZutatenListe.SAHNE.get(),75, EinheitenListe.MILLILITER.get());
    static final ZutatMengeEinheit petersilie = new ZutatMengeEinheit(ZutatenListe.PETERSILIE.get(),1/2d, EinheitenListe.STÄNGEL.get());
    static final ZutatMengeEinheit salz = new ZutatMengeEinheit(ZutatenListe.SALZ.get(),1/4d, EinheitenListe.TEELÖFFEL.get());
    static final ZutatMengeEinheit pfeffer = new ZutatMengeEinheit(ZutatenListe.PFEFFER.get(),1, EinheitenListe.PRISE.get());
    static final ZutatMengeEinheit muskat = new ZutatMengeEinheit(ZutatenListe.MUSKAT.get(),1/2d, EinheitenListe.PRISE.get());
    static final ZutatMengeEinheit schinkenwürfel = new ZutatMengeEinheit(ZutatenListe.SCHINKENWÜRFEL.get(),50, EinheitenListe.GRAMM.get());

    public KartoffelcremeSuppe(){
        super("KartoffelcremeSuppe", kartoffeln,zwiebeln,gemüseBrühe,öl,sahne,petersilie,salz,pfeffer,muskat);
        super.addOptions(schinkenwürfel);
    }
}