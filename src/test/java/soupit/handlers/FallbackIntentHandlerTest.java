package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class FallbackIntentHandlerTest {

    private FallbackIntentHandler handler;

    @Test
    void canHandleTest() {

        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));

    }

    @Test
    void handleTest() {
    }
}