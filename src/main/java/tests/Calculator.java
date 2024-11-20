package tests;

public class Calculator {

    FasterCalculator fasterCalculator = new FasterCalculator();

    String sumMessage(int a, int b) {
        return "Total: " + fasterCalculator.sum(a, b);
    }
}
