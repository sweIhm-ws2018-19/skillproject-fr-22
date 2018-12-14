package soupit.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;


class CancelandStopIntentHandlerTest {

    private CancelandStopIntentHandler handler;

    @Before
    public void setup() {
        handler = new CancelandStopIntentHandler();
    }

    @Test
    public void canHandleTest() {
            final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
            doReturn(true).when(inputMock).matches(any());
            assertTrue(handler.canHandle(inputMock));
    }

    @Test
    void handleTest() {
    }
}