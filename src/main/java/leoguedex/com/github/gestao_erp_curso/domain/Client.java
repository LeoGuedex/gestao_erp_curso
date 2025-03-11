package leoguedex.com.github.gestao_erp_curso.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double balance;

  @ElementCollection
  @CollectionTable(name = "phones_numbers")
  private Set<String> phoneNumbers;

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private List<Orders> orders = new ArrayList<>();

  @Column(length = 100)
  private String name;

  @Column(unique = true)
  @CPF(message = "CPF inválido")
  private String cpf;

  private Long longitude;
  private Long latitude;

  @Column(unique = true)
  @Email(message = "Email inválido")
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Integer age;
}
