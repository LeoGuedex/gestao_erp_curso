package leoguedex.com.github.gestao_erp_curso.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.enums.PaymentType;

public record CreateOrderRequestDTO(@NotNull Long client, @NotBlank String address, @NotNull PaymentType paymentType,
                                    @NotNull List<ReceivedProductsDTO> products) {

}
