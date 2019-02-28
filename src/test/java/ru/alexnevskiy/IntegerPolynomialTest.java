package ru.alexnevskiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPolynomialTest {

    static boolean equalsPolynom(String polynom1, String polynom2) {
        IntegerPolynomial obj1 = new IntegerPolynomial(polynom1);
        IntegerPolynomial obj2 = new IntegerPolynomial(polynom2);
        return obj1.equals(obj2);
    }

    @Test
    void equals() {
        assertTrue(equalsPolynom("x", "x"));
        assertTrue(equalsPolynom("1", "1"));
        assertTrue(equalsPolynom("x^2-5", "-5+x^2"));
        assertTrue(equalsPolynom("5x^6-6x^4+x^2-10", "x^2-10+5x^6-6x^4"));
        assertTrue(equalsPolynom("x^2  +  66x", "66x +    x^2"));
        assertFalse(equalsPolynom("x", "2x"));
        assertFalse(equalsPolynom("1", "11"));
        assertFalse(equalsPolynom("x^2-5", "-x^2+5"));
        assertFalse(equalsPolynom("5x^6-6x^4+x^2-10", "x^2-10+5x^6-6x^7"));
        assertFalse(equalsPolynom("10x     +   4", "15x -   6"));
    }

    static int valueEquals(int x, String polynom) {
        IntegerPolynomial obj = new IntegerPolynomial(polynom);
        return obj.value(x);
    }

    @Test
    void value() {
        assertEquals(2, valueEquals(1, "2x"));
        assertEquals(2, valueEquals(2, "2"));
        assertEquals(10, valueEquals(3, "3x^2-6x+1"));
        assertEquals(-37, valueEquals(2, "-3x^5+6x^2-10x+55"));
        assertEquals(19, valueEquals(2, "4x^2   -    6x   +   15"));
    }

    static void plusEquals(String answer, String polynom1, String polynom2) {
        IntegerPolynomial obj1 = new IntegerPolynomial(answer);
        IntegerPolynomial obj2 = new IntegerPolynomial(polynom1);
        IntegerPolynomial obj3 = new IntegerPolynomial(polynom2);
        assertEquals(obj1, obj2.plus(obj3));
    }

    @Test
    void plus() {
        plusEquals("2x^4-6x^2+3x-2", "x^4+1", "x^4-6x^2+3x-3");
        plusEquals("-3x^5-6x^3-10x^2+3x-85", "-3x^5+6x^4-10x^2-88", "-6x^4-6x^3+3x+3");
        plusEquals("0", "x^2-6", "-x^2+6");
        plusEquals("x^5-10x^3-6x^2+22x-1", "-4x^3-6x^2+12x-1", "x^5-6x^3+10x");
        plusEquals("55x", "25x   +   1", "30x -      1");
    }

    static void minusEquals(String answer, String polynom1, String polynom2) {
        IntegerPolynomial obj1 = new IntegerPolynomial(answer);
        IntegerPolynomial obj2 = new IntegerPolynomial(polynom1);
        IntegerPolynomial obj3 = new IntegerPolynomial(polynom2);
        assertEquals(obj1, obj2.minus(obj3));
    }

    @Test
    void minus() {
        minusEquals("x", "2x", "x");
        minusEquals("15", "40", "25");
        minusEquals("-4x^3+3x^2+6x-5", "3x^2+6x-10", "4x^3-5");
        minusEquals("0", "-x^6+4x^3-2x+1", "1-x^6-2x+4x^3");
        minusEquals("-9x^10+5x^6+4x^2-2", "x^10    +    4x^2   -   2", "10x^10   -   5x^6");
    }

    static void multiplicationEquals(String answer, String polynom1, String polynom2) {
        IntegerPolynomial obj1 = new IntegerPolynomial(answer);
        IntegerPolynomial obj2 = new IntegerPolynomial(polynom1);
        IntegerPolynomial obj3 = new IntegerPolynomial(polynom2);
        assertEquals(obj1, obj2.multiplication(obj3));
    }

    @Test
    void multiplication() {
        multiplicationEquals("x^2", "x", "x");
        multiplicationEquals("-20", "2", "-10");
        multiplicationEquals("200x^7-220x^5-60x^4+80x^3+36x^2-10x-6", "10x^4-6x^2+1", "20x^3-10x-6");
        multiplicationEquals("0", "x^6-150x^4-13x^2+150x-60", "0");
        multiplicationEquals("2x^4-3x^3-24x^2+51x-10", "x^2   +    x   -   10", "2x^2   -   5x   +   1");
    }

    @Test
    void division() {
    }

    @Test
    void remainder() {
    }
}