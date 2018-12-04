package main.java.soupit.handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelandStopIntentHandlerTest extends CancelandStopIntentHandler{

    @Test
    void canHandleTest() {
        assertFalse(super.canHandle(null));
        //assertTrue(super.canHandle("hi"));
    }

    @Test
    void handleTest() {
    }
}