package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HelpIntentHandlerTest extends HelpIntentHandler{

    private HelpIntentHandler handler;


    @org.junit.Test
    void canHandleTest() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    void handleTest() {
    }
}