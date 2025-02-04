package leoguedex.com.github.gestao_erp_curso.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;

public record ClientUpdateRequestDTO(
    Double balance,
    Set<String> phoneNumbers,
    String name,
    Long latitude,
    Long longitude,
    @NotBlank String email,
    Gender gender,
    @NotBlank String password
) {

}
