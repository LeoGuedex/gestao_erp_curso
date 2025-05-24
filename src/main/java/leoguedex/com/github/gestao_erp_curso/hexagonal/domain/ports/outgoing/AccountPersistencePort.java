package leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing;

import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.model.Account;

public interface AccountPersistencePort {
  Account findById(String id);
  void save(Account account);
}
