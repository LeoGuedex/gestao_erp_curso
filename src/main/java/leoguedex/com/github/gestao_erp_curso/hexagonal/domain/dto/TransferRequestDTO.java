package leoguedex.com.github.gestao_erp_curso.hexagonal.domain.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {

  String from;
  String to;
  BigDecimal amount;
}
