package leoguedex.com.github.gestao_erp_curso.hexagonal.service;

import java.math.BigDecimal;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.model.Account;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.incoming.TransferUseCase;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing.AccountPersistencePort;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing.AntiFraudCheckPort;

public class TransferService implements TransferUseCase {

  private final AccountPersistencePort persistence;
  private final AntiFraudCheckPort antiFraud;

  public TransferService(AccountPersistencePort persistence, AntiFraudCheckPort antiFraud) {
    this.persistence = persistence;
    this.antiFraud = antiFraud;
  }

  @Override
  public void transfer(String from, String to, BigDecimal amount) {
    if (antiFraud.isSuspicious(from, amount)) {
      throw new IllegalStateException("Transacao suspeita");
    }

    Account source = persistence.findById(from);
    Account target = persistence.findById(to);

    source.debit(amount);
    target.credit(amount);

    persistence.save(source);
    persistence.save(target);
  }
}
