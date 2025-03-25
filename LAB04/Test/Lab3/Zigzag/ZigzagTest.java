package Lab3.Zigzag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZigzagTest {
    Zigzag zigzag;

    @BeforeEach
    void setUp(){
        zigzag = new Zigzag();
    }

    @Test
    public void testZigzag_it_regularZigzag() {
        String input = "PAYPALISHIRING";
        int numRows = 3;
        String expected = "PAHNAPLSIIGYIR";

        assertEquals(expected, zigzag.zigzag_it(input, numRows));
    }

    @Test
    public void testZigzag_it_singleRow() {
        String input = "ABCD";
        int numRows = 1;

        assertEquals("ABCD", zigzag.zigzag_it(input, numRows));
    }

    @Test
    public void testZigzag_it_emptyString() {
        String input = "";
        int numRows = 3;

        assertEquals("", zigzag.zigzag_it(input, numRows));
    }

    @Test
    public void testZigzag_it_singleCharacter() {
        String input = "A";
        int numRows = 3;

        assertEquals("A", zigzag.zigzag_it(input, numRows));
    }

}