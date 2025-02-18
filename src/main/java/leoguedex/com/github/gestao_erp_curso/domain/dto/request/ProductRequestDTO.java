package leoguedex.com.github.gestao_erp_curso.domain.dto.request;

import leoguedex.com.github.gestao_erp_curso.domain.enums.ProductType;

public record ProductRequestDTO(
    Double price,
    String name,
    ProductType productType
) {

}
