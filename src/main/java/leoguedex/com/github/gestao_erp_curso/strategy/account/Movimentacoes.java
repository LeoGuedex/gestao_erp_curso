package leoguedex.com.github.gestao_erp_curso.strategy.account;

public class Movimentacoes {

  private AccountStrategy strategy;

  public Movimentacoes(AccountStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(AccountStrategy strategy) {
    this.strategy = strategy;
  }

  public double realizaInvestimento(double valor) {
    return strategy.investimento(valor);
  }

  public double realizaSaque(double valor) {
    return strategy.saque(valor);
  }
}
