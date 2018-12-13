package soupit.handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FallbackIntentHandlerTest extends FallbackIntentHandler{

    @Test
    void canHandleTest() {
        assertFalse(super.canHandle(null));
    }

    @Test
    void handleTest() {
    }
}