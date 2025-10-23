package uz.pdp.dbcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a , b",
            "1, 2",
            "2, 3",
            "12, 13",
            "24, 31"


    }, useHeadersInDisplayName = true)
    public void testAdd(int a, int b) {
        Assertions.assertEquals(a + b, calculator.add(a, b));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testcases.csv", useHeadersInDisplayName = true)
    public void testAddByCsvFile(int a, int b) {
        Assertions.assertEquals(a + b, calculator.add(a, b));
    }

    @Test
    public void should_done_between_3_seconds(int i) {
        int a = 12;
        Assertions.assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> {
            calculator.add(a, i);
        });
    }

    @Test
    public void test_subtract() {
        int a = 12;
        int b = 13;
        int expectedResult = -1;
        Assertions.assertEquals(expectedResult, calculator.sub(a, b));

    }

    @Test
    public void testMultiply() {
        int a = 12;
        int b = 13;
        int expectedResult = 156;
        Assertions.assertEquals(expectedResult, calculator.mul(a, b));

    }

    @Test
    public void testDivide() {
        int a = 12;
        int b = 13;
        int expectedResult = 12 / 13;
        Assertions.assertEquals(expectedResult, calculator.div(a, b));
    }

    @Test
    public void should_throw_exception_when_divide_by_zero() {
        int a = 12;
        int b = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.div(a, b));
    }


}


// set up
// execution
// assertion