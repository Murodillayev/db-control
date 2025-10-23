package uz.pdp.dbcontrol;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uz.pdp.dbcontrol.test.Child2;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} : {totalRepetitions}")
    @DisplayName("test add")
    public void testAdd() {
        int a = new Random().nextInt(100);
        int b = new Random().nextInt(100);
        int expectedResult = a + b;
        double result = calculator.add(a, b);
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void should_done_between_3_seconds(int i) {
        int a = 12;
        Assertions.assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> {
            calculator.add(a, i);
        });
    }

    @ParameterizedTest
    @ValueSource(classes = {Child1.class, Child2.class})
    public void testValueSource(Class<Base> baseClass) {

        Assertions.assertEquals(baseClass.getPackageName(), "uz.pdp.dbcontrol");
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