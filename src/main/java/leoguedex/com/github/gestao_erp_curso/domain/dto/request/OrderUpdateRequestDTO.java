package leoguedex.com.github.gestao_erp_curso.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateRequestDTO(@NotNull Integer clientId, @NotBlank String address) {

}
