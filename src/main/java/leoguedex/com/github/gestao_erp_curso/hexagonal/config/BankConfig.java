package leoguedex.com.github.gestao_erp_curso.hexagonal.config;

import leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.out.antifraud.AntiFraudAdapter;
import leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.out.persistence.AccountRepositoryAdapter;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.incoming.TransferUseCase;
import leoguedex.com.github.gestao_erp_curso.hexagonal.service.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfig {

  @Bean
  public TransferUseCase transferUseCase() {
    return new TransferService(new AccountRepositoryAdapter(), new AntiFraudAdapter());
  }
}
