package leoguedex.com.github.gestao_erp_curso.strategy.account;

public class Main {

  public static void main(String[] args) {
    Movimentacoes mov = new Movimentacoes(new ContaSalario());
    System.out.println("Resultado pós Investimento: " + mov.realizaInvestimento(1000));
    System.out.println("Resultado pós Saque: " + mov.realizaSaque(50));

    mov.setStrategy(new ContaVanGoogh());
    System.out.println("=============================================");
    System.out.println("Resultado pós Investimento: " + mov.realizaInvestimento(1000));
    System.out.println("Resultado pós Saque: " + mov.realizaSaque(50));

    mov.setStrategy(new ContaSelect());
    System.out.println("=============================================");
    System.out.println("Resultado pós Investimento: " + mov.realizaInvestimento(1000));
    System.out.println("Resultado pós Saque: " + mov.realizaSaque(50));
  }
}
