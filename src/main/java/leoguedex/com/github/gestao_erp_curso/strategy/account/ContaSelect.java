package leoguedex.com.github.gestao_erp_curso.strategy.account;

public class ContaSelect implements AccountStrategy {

  @Override
  public double investimento(Double valorInvestido) {
    //  double taxaSelect = obtemTaxaJurosContaSelect()
    //  Empresa empresa = identificaEmpresa()
    //  Pessoa pessoa = empresa.getRepresentante()
    //  pessoa.realizaInvestimento(valorInvestido, taxaSelect)
    return valorInvestido * 1.3;
  }

  @Override
  public double saque(Double valorSacado) {
    //  Empresa empresa = identificaEmpresa()
    // validaContaEmpresaSeTemSaldo(empresa)
    // Pessoa pessoa = empresa.getRepresentante()
    // obtemAutorizacaoBACEN(empresa)
    // realizaSaqueContaSelect(empresa, valorSacado)
    return 100 - valorSacado;
  }
}
