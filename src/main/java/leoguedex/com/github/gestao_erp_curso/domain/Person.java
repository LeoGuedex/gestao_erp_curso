package leoguedex.com.github.gestao_erp_curso.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter // Gera os métodos getters
@Setter // Gera os métodos setters
@NoArgsConstructor // Gera o construtor sem argumentos
@AllArgsConstructor  // Gera o construtor com todos os argumentos
@Entity // Indica que a classe é uma entidade
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  private String name;

  @CPF(message = "CPF inválido")
  private String cpf;

  private Long longitude;
  private Long latitude;

  @Email(message = "Email inválido")
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Integer age;
}
