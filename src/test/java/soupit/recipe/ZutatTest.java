package soupit.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZutatTest {
    private String testEinzahl = "kartoffel";
    private String testMehrzahl = "kartoffeln";

    private Zutat nullTest = new Zutat(null,null);
    private Zutat einzahlTest = new Zutat(testEinzahl,null);
    private Zutat einzahlTestTrue = new Zutat(testEinzahl,null);
    private Zutat mehrzahlTest = new Zutat(testMehrzahl,null);
    private Zutat mehrzahlTestTrue = new Zutat(testMehrzahl,null);



    @Test
    void equalsTest() {
        assertTrue(einzahlTest.equals(einzahlTestTrue));
        assertTrue(mehrzahlTest.equals(mehrzahlTestTrue));
        assertFalse(einzahlTest.equals(mehrzahlTest));
    }

    @Test
    void toStringTest() {
        assertNull(nullTest.toString());
        assertEquals(testEinzahl,einzahlTest.toString());
    }
}