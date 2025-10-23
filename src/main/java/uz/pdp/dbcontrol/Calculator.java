package uz.pdp.dbcontrol;

import lombok.SneakyThrows;

public class Calculator {


    @SneakyThrows
    public double add(double a, double b) {

        Thread.sleep(2000);
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}
