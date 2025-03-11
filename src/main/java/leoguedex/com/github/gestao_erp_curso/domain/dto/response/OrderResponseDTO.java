package leoguedex.com.github.gestao_erp_curso.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import leoguedex.com.github.gestao_erp_curso.domain.Client;
import leoguedex.com.github.gestao_erp_curso.domain.enums.PaymentType;

public record OrderResponseDTO(
    Long id,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") String instance,
    Client client,
    String address,
    PaymentType paymentType) {

}
