package leoguedex.com.github.gestao_erp_curso.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

  private Set<String> phoneNumbers;

  @NotBlank
  @Length(max = 100, min = 5)
  private String name;

  @NotBlank
  @CPF(message = "CPF inválido")
  private String cpf;

  private Long longitude;
  private Long latitude;

  @NotBlank
  @Email(message = "Email inválido")
  private String email;

  @NotBlank
  private String password;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Integer age;
}
