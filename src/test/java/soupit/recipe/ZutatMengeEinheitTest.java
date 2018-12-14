package soupit.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class ZutatMengeEinheitTest {

    private Einheit standardEinheit = new Einheit("kilo","n");
    //private Einheit nullEinheit = new Einheit("null","");
    private Einheit leereEinheit = new Einheit("","");




    private Zutat standardZutat = new Zutat("milch","f");
    //private Zutat standardZutatTrue = new Zutat("milch","f");
    //private Zutat nullZutat = new Zutat(null,null);
    private Zutat leereZutat = new Zutat("","");
    private Zutat kartoffelZutat = new Zutat("kartoffel","f");
    private Zutat zwiebelZutat = new Zutat("zwiebel","f");
    private Zutat männlicheZutat = new Zutat("pfeffer","m");







    private ZutatMengeEinheit standardTest = new ZutatMengeEinheit(standardZutat,1,standardEinheit);
    private ZutatMengeEinheit standardMehrzahl = new ZutatMengeEinheit(standardZutat,2,standardEinheit);
    private ZutatMengeEinheit standardHalbe = new ZutatMengeEinheit(standardZutat,0.5,standardEinheit);
    private ZutatMengeEinheit nullTest = new ZutatMengeEinheit(null,0,null);
    private ZutatMengeEinheit leererTest = new ZutatMengeEinheit(leereZutat,2,leereEinheit);
    private ZutatMengeEinheit kartoffelEinzahl = new ZutatMengeEinheit(kartoffelZutat,1,standardEinheit);
    private ZutatMengeEinheit kartoffelMehrzahl = new ZutatMengeEinheit(kartoffelZutat,2,standardEinheit);
    private ZutatMengeEinheit zwiebelMehrzahl = new ZutatMengeEinheit(zwiebelZutat,2,standardEinheit);
    private ZutatMengeEinheit männlichEinzahl = new ZutatMengeEinheit(männlicheZutat,1,standardEinheit);
    private ZutatMengeEinheit männlichMehrzahl = new ZutatMengeEinheit(männlicheZutat,2,standardEinheit);
    private ZutatMengeEinheit männlichHalbe = new ZutatMengeEinheit(männlicheZutat,0.5,standardEinheit);



    @Test
    void zutatToStringTest() {
        assertEquals("milch", standardTest.zutatToString());
        //assertNull(nullTest.zutatToString());
        assertEquals("",leererTest.zutatToString());
        assertEquals("kartoffel",kartoffelEinzahl.zutatToString());
        assertEquals("kartoffeln",kartoffelMehrzahl.zutatToString());
        assertEquals("zwiebeln",zwiebelMehrzahl.zutatToString());
    }

    @Test
    void mengeToStringTest() {
        assertEquals("ein",standardTest.mengeToString());
        assertEquals("2",standardMehrzahl.mengeToString());
        assertEquals("ein halbes",standardHalbe.mengeToString());
        //assertEquals("ein",nullTest.mengeToString());
        assertEquals("ein",männlichEinzahl.mengeToString());
        assertEquals("2",männlichMehrzahl.mengeToString());
        assertEquals("ein halbes",männlichHalbe.mengeToString());
    }
    @Test
    void getZutatTest() {
        assertNull(nullTest.getZutat());
        assertEquals(standardZutat,standardTest.getZutat());
    }
}