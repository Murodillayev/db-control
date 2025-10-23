package uz.pdp.dbcontrol;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.function.Executable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @Order(6)
    @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_11, JRE.JAVA_10, JRE.JAVA_9})
    public void testAdd() {
        int a = 12;
        int b = 13;
        int expectedResult = 25;

        double result = calculator.add(a, b);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @Order(5)
    @DisabledOnOs(value = OS.WINDOWS)
    public void should_done_between_3_seconds() {
        int a = 12;
        int b = 13;
        Assertions.assertTimeout(Duration.of(3, ChronoUnit.SECONDS), () -> {
            calculator.add(a, b);
        });
    }

    @Test
    @Order(4)
    @EnabledOnOs(value = OS.WINDOWS)
    public void test_subtract() {
        int a = 12;
        int b = 13;
        int expectedResult = -1;
        Assertions.assertEquals(expectedResult, calculator.sub(a, b));

    }

    @Test
    @Order(3)
    @EnabledOnOs(value = {OS.MAC, OS.LINUX, OS.WINDOWS, OS.AIX})
    @DisabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_23)
    public void testMultiply() {
        int a = 12;
        int b = 13;
        int expectedResult = 156;
        Assertions.assertEquals(expectedResult, calculator.mul(a, b));

    }

    @Test
    @Order(2)
    @DisabledIf(value = "disableBolsinmi")
    public void testDivide() {
        int a = 12;
        int b = 13;
        int expectedResult = 12 / 13;
        Assertions.assertEquals(expectedResult, calculator.div(a, b));
    }

    @Test
    @Order(1)
    @Disabled
    public void should_throw_exception_when_divide_by_zero() {
        int a = 12;
        int b = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.div(a, b));
    }

    boolean disableBolsinmi() {
        return new Random().nextBoolean();
    }


}


// set up
// execution
// assertion