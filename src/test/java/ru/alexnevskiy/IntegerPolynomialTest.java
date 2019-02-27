package ru.alexnevskiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPolynomialTest {
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
    }

    @Test
    void equals() {
    }

    @Test
    void minus() {
    }

    @Test
    void value() {
    }

    @Test
    void multiplication() {
    }

    @Test
    void division() {
    }

    @Test
    void remainder() {
    }
}