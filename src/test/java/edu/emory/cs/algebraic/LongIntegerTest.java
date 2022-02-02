package edu.emory.cs.algebraic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class LongIntegerTest {
    @Test
    public void testConstructors() {

        // default constructor
        assertEquals("0", new LongInteger().toString());

        // constructor with a string parameter
        assertEquals("12", new LongInteger("12").toString());
        assertEquals("34", new LongInteger("+34").toString());
        assertEquals("-56", new LongInteger("-56").toString());
        assertEquals("-0", new LongInteger("-0").toString());

        // copy constructor
        assertEquals("12", new LongInteger(new LongInteger("12")).toString());
        assertEquals("-34", new LongInteger(new LongInteger("-34")).toString());
    }

    @Test
    public void testMultiply() {
        LongInteger a = new LongInteger("123456789");

        a.multiply(new LongInteger("1"));
        assertEquals("123456789", a.toString());

        a.multiply(new LongInteger("-1"));
        assertEquals("-123456789", a.toString());

        a.multiply(new LongInteger("-1234567890123456789"));
        assertEquals("152415787517146788750190521", a.toString());

        a.multiply(new LongInteger("0"));
        assertEquals("0", a.toString());

        a.multiply(new LongInteger("-0"));
        assertEquals("-0", a.toString());
    }

    @Test
    public void testCompareTo() {
        assertTrue(0 < new LongInteger("0").compareTo(new LongInteger("-0")));
        assertTrue(0 > new LongInteger("-0").compareTo(new LongInteger("0")));

        assertTrue(0 < new LongInteger("12").compareTo(new LongInteger("-34")));
        assertTrue(0 > new LongInteger("-12").compareTo(new LongInteger("34")));

        assertTrue(0 > new LongInteger("-34").compareTo(new LongInteger("12")));
        assertTrue(0 < new LongInteger("34").compareTo(new LongInteger("-12")));
    }

    @Test
    public void testAddDifferentSign(){
        LongInteger x = new LongInteger("2000");
        x.add(new LongInteger("-1999"));
        assertEquals("1", x.toString());

        LongInteger y = new LongInteger("-2000");
        y.add(new LongInteger("1999"));
        assertEquals("-1", y.toString());

        LongInteger z = new LongInteger("1999");
        z.add(new LongInteger("-2000"));
        assertEquals("-1", z.toString());

        LongInteger w = new LongInteger("-1999");
        w.add(new LongInteger("2000"));
        assertEquals("1", w.toString());
    }
}
