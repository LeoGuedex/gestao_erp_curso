package leoguedex.com.github.gestao_erp_curso.hexagonal.domain.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private String id;
  private BigDecimal balance;
  
  public void debit(BigDecimal amount){
    balance = balance.subtract(amount);
  }
  
  public void credit(BigDecimal amount){
    balance = balance.add(amount);
  }
}
