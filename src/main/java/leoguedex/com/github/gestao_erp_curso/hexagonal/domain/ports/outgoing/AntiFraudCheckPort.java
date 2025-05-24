package leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing;

import java.math.BigDecimal;

public interface AntiFraudCheckPort {

  boolean isSuspicious(String accountId, BigDecimal amount);
}
