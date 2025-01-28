package leoguedex.com.github.gestao_erp_curso.domain.dto.response;

import java.util.List;
import java.util.Set;
import leoguedex.com.github.gestao_erp_curso.domain.Orders;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;

public record ClientResponseDTO(
    Long id,
    Double balance,
    Set<String> phoneNumbers,
    List<Orders> orders,
    String name,
    String cpf,
    Long longitude,
    Long latitude,
    String email,
    Gender gender,
    Integer age
) {

}
