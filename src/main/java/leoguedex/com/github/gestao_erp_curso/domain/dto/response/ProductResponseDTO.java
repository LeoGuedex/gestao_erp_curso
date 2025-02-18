package leoguedex.com.github.gestao_erp_curso.domain.dto.response;

import leoguedex.com.github.gestao_erp_curso.domain.enums.ProductType;

public record ProductResponseDTO(
    Long id,
    Double price,
    String name,
    ProductType productType
) {

}
