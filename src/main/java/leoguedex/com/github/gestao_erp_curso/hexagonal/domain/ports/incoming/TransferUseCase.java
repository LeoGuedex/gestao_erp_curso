package leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.incoming;

import java.math.BigDecimal;

public interface TransferUseCase {

  void transfer(String from, String to, BigDecimal amount);
}
