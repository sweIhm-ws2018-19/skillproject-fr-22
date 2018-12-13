package soupit.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EinheitsTest {
    private String test = "test";

    private Einheit nullTest = new Einheit(null,null);
    private Einheit stringTest = new Einheit(test,test);

    void toStringTest() {
        assertNull(nullTest.toString());
        assertEquals(test,stringTest.toString());
    }




}