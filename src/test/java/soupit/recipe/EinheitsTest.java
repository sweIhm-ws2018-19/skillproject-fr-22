package soupit.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EinheitsTest {
    private String test = "test";

    private Einheit nullTest = new Einheit(null,null);
    private Einheit stringTest = new Einheit(test,test);

    @Test
    void toStringTest() {
        assertNull(nullTest.toString());
        assertEquals(test,stringTest.toString());
    }
    @Test
    void getEinheitTest() {
        assertNull(nullTest.getEinheit());
        assertEquals(test,stringTest.getEinheit());
    }
    @Test
    void getGrammatGeschlechtTest() {
        assertNull(nullTest.getGrammatGeschlecht());
        assertEquals(test,stringTest.getGrammatGeschlecht());
    }
}