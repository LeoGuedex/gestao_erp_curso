package leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.out.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.model.Account;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing.AccountPersistencePort;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryAdapter implements AccountPersistencePort {

  private Map<String, Account> database = createDatabase();

  @Override
  public Account findById(String id) {
    return database.get(id);
  }

  @Override
  public void save(Account account) {
    database.put(account.getId(), account);
  }

  public Map<String, Account> createDatabase() {
    Account ac1 = new Account("A", new BigDecimal("1000"));
    Account ac2 = new Account("B", new BigDecimal("500"));
    Account ac3 = new Account("C", new BigDecimal("10"));

    Map<String, Account> database = new HashMap<>();
    database.put(ac1.getId(), ac1);
    database.put(ac2.getId(), ac2);
    database.put(ac3.getId(), ac3);

    return database;
  }
}
