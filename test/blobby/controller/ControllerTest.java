package blobby.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    @Test
    public void translatingShouldGiveCorrectPosition() {
        assertEquals(109, Controller.translate(1089));
        assertEquals(123, Controller.translate(1234));
        assertEquals(508, Controller.translate(5076));
    }
}
