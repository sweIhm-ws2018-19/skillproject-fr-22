package soupit.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RezeptTest {
    private String test = "test";

    private Einheit testEinheit = new Einheit(null,null);

    private Zutat istDabei = new Zutat("milch","f");
    private Zutat istAuchDabei = new Zutat("kartoffel","f");
    private Zutat istNichtDabei = new Zutat("orangensaft","m");

    private Zutat[] zutatenArray = {istDabei,istAuchDabei};

    private ZutatMengeEinheit dabei = new ZutatMengeEinheit(istDabei,1,testEinheit);
    private ZutatMengeEinheit nichtDabei = new ZutatMengeEinheit(istNichtDabei,1,testEinheit);
    private ZutatMengeEinheit auchDabei = new ZutatMengeEinheit(istAuchDabei,1,testEinheit);

    private ZutatMengeEinheit[] nichtDabeiTest = {nichtDabei};
    private ZutatMengeEinheit[] eineDabeiTest = {nichtDabei,dabei};
    private ZutatMengeEinheit[] alleDabeiTest = {dabei,auchDabei};

    private Rezept nullTest = new Rezept(null,null);
    private Rezept stringTest = new Rezept(test,null);
    private Rezept nullAccordance = new Rezept(test,nichtDabeiTest);
    private Rezept einAccordance = new Rezept(test,eineDabeiTest);
    private Rezept zweiAccordance = new Rezept(test,alleDabeiTest);

    @Test
    void checkAccordance() {
        assertEquals(0,nullAccordance.checkAccordance(zutatenArray));
        assertEquals(1,einAccordance.checkAccordance(zutatenArray));
        assertEquals(2,zweiAccordance.checkAccordance(zutatenArray));
    }

    @Test
    void addOptions() {
        assertNull(nullTest.toString());
        assertEquals(test,stringTest.toString());
    }
}