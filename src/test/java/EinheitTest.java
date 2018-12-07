class EinheitTest {

    Einheit test1 = new Einheit(null,null);
    Einheit test2 = new Einheit("test",null);


    @org.junit.jupiter.api.Test
    void toStringTest() {
        assertEquals(test1.einheit,test1.toString());
        assertEquals(test2.einheit,test2.toString());

    }
}