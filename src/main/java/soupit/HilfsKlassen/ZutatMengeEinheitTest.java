package main.java.soupit.HilfsKlassen;

import org.junit.jupiter.api.Test;

class ZutatMengeEinheitTest {

    private Einheit standardEinheit = new Einheit("kilo","n");
    private Einheit nullEinheit = new Einheit(null,null);
    private Einheit leereEinheit = new Einheit("","");




    private Zutat standardZutat = new Zutat("milch","f");
    private Zutat nullZutat = new Zutat(null,null);
    private Zutat leereZutat = new Zutat("","");
    private Zutat kartoffelZutat = new Zutat("kartoffel","f");
    private Zutat männlicheZutat = new Zutat("pfeffer","m");








    private ZutatMengeEinheit standardTest = new ZutatMengeEinheit(standardZutat,1,standardEinheit);
    private ZutatMengeEinheit standardMehrzahl = new ZutatMengeEinheit(standardZutat,2,standardEinheit);
    private ZutatMengeEinheit standardHalbe = new ZutatMengeEinheit(standardZutat,0.5,standardEinheit);
    private ZutatMengeEinheit nullTest = new ZutatMengeEinheit(nullZutat,2,nullEinheit);
    private ZutatMengeEinheit leererTest = new ZutatMengeEinheit(leereZutat,2,leereEinheit);
    private ZutatMengeEinheit kartoffelEinzahl = new ZutatMengeEinheit(kartoffelZutat,1,standardEinheit);
    private ZutatMengeEinheit kartoffelMehrzahl = new ZutatMengeEinheit(kartoffelZutat,2,standardEinheit);
    private ZutatMengeEinheit männlichEinzahl = new ZutatMengeEinheit(männlicheZutat,2,standardEinheit);
    private ZutatMengeEinheit männlichMehrzahl = new ZutatMengeEinheit(männlicheZutat,2,standardEinheit);
    private ZutatMengeEinheit männlichHalbe = new ZutatMengeEinheit(männlicheZutat,0.5,standardEinheit);




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
        assertEquals("eine",standardTest.mengeToString());
        assertEquals("2",standardMehrzahl.mengeToString());
        assertEquals("eine halbe",standardHalbe.mengeToString());
        assertEquals("2",nullTest.mengeToString());
        assertEquals("ein",männlichEinzahl.mengeToString());
        assertEquals("2",männlichMehrzahl.mengeToString());
        assertEquals("einen halben",männlichHalbe.mengeToString());
    }

    @Test
    void einheitToStringTest() {
    }
}