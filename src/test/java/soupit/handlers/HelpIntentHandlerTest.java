package soupit.handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class HelpIntentHandlerTest extends HelpIntentHandler{

    @Test
    void canHandleTest() {
        String test = "test";
        assertFalse(super.canHandle(null));
    }

    @Test
    void handleTest() {
    }
}