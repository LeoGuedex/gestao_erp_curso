package leoguedex.com.github.gestao_erp_curso.strategy.account;

public class ContaSalario implements AccountStrategy {

  @Override
  public double investimento(Double valorInvestido) {
    //  double taxa = obtemTaxaJurosContaSalario()
    //  Pessoa pessoaLogada = identificaUsuario()
    //  pessoaLogada.realizaInvestimento(valorInvestido, taxa)
    return valorInvestido * 1.01;
  }

  @Override
  public double saque(Double valorSacado) {
    // validaContaUsuarioSeTemSaldo()
    // obtemAutorizacaoBACEN()
    // realizaSaqueContaSalario(valorSacado)
    return 100 - (valorSacado + 2.50);
  }
}
