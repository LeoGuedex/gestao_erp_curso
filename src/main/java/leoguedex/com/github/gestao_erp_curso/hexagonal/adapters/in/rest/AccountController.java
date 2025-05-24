package leoguedex.com.github.gestao_erp_curso.hexagonal.adapters.in.rest;

import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.dto.TransferRequestDTO;
import leoguedex.com.github.gestao_erp_curso.hexagonal.domain.ports.incoming.TransferUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final TransferUseCase transferUseCase;

  public AccountController(TransferUseCase transferUseCase) {
    this.transferUseCase = transferUseCase;
  }

  @PostMapping("/transfer")
  public ResponseEntity<Void> transfer(@RequestBody TransferRequestDTO request) {
    transferUseCase.transfer(request.getFrom(), request.getTo(), request.getAmount());
    return ResponseEntity.ok().build();
  }
}
