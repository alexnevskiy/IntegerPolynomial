package ru.alexnevskiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPolynomialTest {

    @Test
    void plus() {
        assertEquals(new IntegerPolynomial("2x^4-6x^2+3x-2"), new IntegerPolynomial("x^4+1").plus(new IntegerPolynomial("x^4-6x^2+3x-3")));
        assertEquals(new IntegerPolynomial("1"), new IntegerPolynomial("0").plus(new IntegerPolynomial("1")));
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