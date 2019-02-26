package ru.alexnevskiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPolynomialTest {

    @Test
    void foolCheck() {
    }

    @Test
    void valueCalculation() {
        assertEquals(2, valueCalculation("x", "2"));
    }

    @Test
    void polynomEquals() {
        IntegerPolynomial polynomial = new IntegerPolynomial();
        assertEquals("Полиномы равны", IntegerPolynomial.polynomEquals("x^2"));
    }

    @Test
    void additionOfPolynomials() {
    }

    @Test
    void subtractionOfPolynomials() {
    }

    @Test
    void multiplicationOfPolynomials() {
    }

    @Test
    void divisionOfPolynomials() {
    }

    @Test
    void remainderOfDivision() {
    }
}