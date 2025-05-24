package leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.out.antifraud;

import java.math.BigDecimal;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.outgoing.AntiFraudCheckPort;
import org.springframework.stereotype.Component;

@Component
public class AntiFraudAdapter implements AntiFraudCheckPort {

  @Override
  public boolean isSuspicious(String accountId, BigDecimal amount) {
    return amount.compareTo(new BigDecimal("10000")) > 0;
  }
}
