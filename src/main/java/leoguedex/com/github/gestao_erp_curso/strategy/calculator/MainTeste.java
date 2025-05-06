package leoguedex.com.github.gestao_erp_curso.strategy.calculator;

public class MainTeste {

  public static void main(String[] args) {
    Calculator calc = new Calculator(new Multiplication());
    System.out.println("Resultado Com Sum: " + calc.calculate(6, 5));

    calc.setStrategy(new Minus());
    System.out.println("Resultado Apos Mudança: " + calc.calculate(6, 5));
  }
}
