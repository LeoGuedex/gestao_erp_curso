package leoguedex.com.github.gestao_erp_curso.strategy.account;

public class ContaVanGoogh implements AccountStrategy {

  @Override
  public double investimento(Double valorInvestido) {
    //  double taxaVanGoogh = obtemTaxaJurosContaVanGoogh()
    //  Pessoa pessoaLogada = identificaUsuario()
    //  pessoaLogada.realizaInvestimento(valorInvestido, taxaVanGoogh)
    return valorInvestido * 1.1;
  }

  @Override
  public double saque(Double valorSacado) {
    // validaContaUsuarioSeTemSaldo()
    // obtemAutorizacaoBACEN()
    // realizaSaqueContaVanGoogh(valorSacado)
    return 100 - (valorSacado + 0.50);
  }
}
