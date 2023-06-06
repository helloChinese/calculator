package test;


public class Test {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(6.0);
        System.out.println(calculator.getResult());

        calculator.subtract(13.0);
        System.out.println(calculator.getResult());
        calculator.multiply(0);
        calculator.redo();
        System.out.println(calculator.getResult());
        calculator.undo();
        System.out.println(calculator.getResult());
        calculator.undo();
        System.out.println(calculator.getResult());
    }
}
