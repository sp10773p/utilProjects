package test.java8;

public class OopAnotherExample {
    public static void main(String[] args) {
        final CalculatorServie calculatorServie = new CalculatorServie();
        int result = calculatorServie.calculate(1, 1);
        System.out.println(result);


    }
}

class CalculatorServie {
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
