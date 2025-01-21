package leoguedex.com.github.gestao_erp_curso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import leoguedex.com.github.gestao_erp_curso.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private String instance;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person client;

  private String address;

  @Enumerated(EnumType.STRING)
  private PaymentType paymentType;

  @OneToMany(mappedBy = "id.order")
  private Set<ItemOrder> itens = new HashSet<>();

  public Order(Client client, String address, PaymentType paymentType) {
    this.client = client;
    this.address = address;
    this.paymentType = paymentType;
  }

  public Double getTotalOrderPrice() {
    return itens.stream().mapToDouble(ItemOrder::getPrice).reduce(0.0, Double::sum);
  }
}
