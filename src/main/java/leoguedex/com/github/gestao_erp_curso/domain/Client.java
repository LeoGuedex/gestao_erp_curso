package leoguedex.com.github.gestao_erp_curso.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client extends Person {

  private Double balance;

  @ElementCollection
  @CollectionTable(name = "phones_numbers")
  private Set<String> phoneNumber;

  @OneToMany(mappedBy = "client")
  private List<Order> orders = new ArrayList<>();

}
