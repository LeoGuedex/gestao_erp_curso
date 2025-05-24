//package leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.in.scheduler;
//
//import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.incoming.BalanceCheckerUseCase;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BalanceCheckerJob {
//
//  private final BalanceCheckerUseCase useCase;
//
//  public BalanceCheckerJob(BalanceCheckerUseCase useCase) {
//    this.useCase = useCase;
//  }
//
//  @Scheduled(fixedDelay = 60000)
//  public void run(){
//    useCase.checkAllBalances();
//  }
//}
