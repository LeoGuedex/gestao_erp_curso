package leoguedex.com.github.gestao_erp_curso.strategy.calculator;

public class Calculator {

  private OperationStrategy operation;

  public Calculator(OperationStrategy operation) {
    this.operation = operation;
  }

  public void setStrategy(OperationStrategy operation){
    this.operation = operation;
  }

  public int calculate(int a, int b){
    return operation.execute(a, b);
  }
}
