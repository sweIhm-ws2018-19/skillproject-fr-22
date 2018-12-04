package main.java.soupit.HilfsKlassen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZutatMengeEinheitTest {

    private Einheit standardEinheit = new Einheit("kilo","n");
    private Einheit nullEinheit = new Einheit(null,null);
    private Einheit leereEinheit = new Einheit("","");




    private Zutat standardZutat = new Zutat("milch","f");
    private Zutat nullZutat = new Zutat(null,null);
    private Zutat leereZutat = new Zutat("","");
    private Zutat kartoffelZutat = new Zutat("kartoffel","f");






    private ZutatMengeEinheit standardTest = new ZutatMengeEinheit(standardZutat,2,standardEinheit);
    private ZutatMengeEinheit nullTest = new ZutatMengeEinheit(nullZutat,2,nullEinheit);
    private ZutatMengeEinheit leererTest = new ZutatMengeEinheit(leereZutat,2,leereEinheit);
    private ZutatMengeEinheit kartoffelEinzahl = new ZutatMengeEinheit(kartoffelZutat,1,standardEinheit);
    private ZutatMengeEinheit kartoffelMehrzahl = new ZutatMengeEinheit(kartoffelZutat,2,standardEinheit);




    @Test
    void zutatToStringTest() {
        assertEquals("milch", standardTest.zutatToString());
        assertNull(nullTest.zutatToString());
        assertEquals("",leererTest.zutatToString());
        assertEquals("kartoffel",kartoffelEinzahl.zutatToString());
        assertEquals("kartoffeln",kartoffelMehrzahl.zutatToString());
    }

    @Test
    void mengeToStringTest() {
    }

    @Test
    void einheitToStringTest() {
    }
}