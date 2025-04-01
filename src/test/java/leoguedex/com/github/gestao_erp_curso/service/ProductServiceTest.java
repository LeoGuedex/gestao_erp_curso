package leoguedex.com.github.gestao_erp_curso.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ProductRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ProductResponseDTO;
import leoguedex.com.github.gestao_erp_curso.domain.enums.ProductType;
import leoguedex.com.github.gestao_erp_curso.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  private ProductService productService;

  private ProductRequestDTO productRequestDTO;
  private ProductResponseDTO productResponseDTO;

  @BeforeEach
  void setUp() {
    productRequestDTO = new ProductRequestDTO(10.0, "Oleo", ProductType.FOOD);
    productResponseDTO = new ProductResponseDTO(1L, 10.0, "Oleo", ProductType.FOOD);
  }

  @Test
  void deveriaCriarUmProdutoComSucesso() {
    when(productService.createProduct(any(ProductRequestDTO.class))).thenReturn(productResponseDTO);

    ProductResponseDTO result = productService.createProduct(productRequestDTO);

    assertNotNull(result);
    assertEquals(productResponseDTO.id(), result.id());
    assertEquals(productResponseDTO.name(), result.name());
    assertEquals(productResponseDTO.price(), result.price());
  }

  @Test
  void deveriaLocalizarUmProdutoPeloId() {
    when(productService.findById(1L)).thenReturn(productResponseDTO);

    ProductResponseDTO result = productService.findById(1L);

    assertNotNull(result);
    assertEquals(1L, result.id());
  }

  @Test
  void deveriaRetornarUmaExceptionQuandoIdNaoExistir(){
    when(productService.findById(99L)).thenThrow(new ProductNotFoundException("Produto com id 99 não encontrado"));

    ProductNotFoundException result = assertThrows(ProductNotFoundException.class, () -> productService.findById(99L));
    assertEquals("Produto com id 99 não encontrado", result.getMessage());
  }

  @Test
  void deveriaApagarUmProdutoPorId(){
    doNothing().when(productService).deleteProduct(1L);
    assertDoesNotThrow(() -> productService.deleteProduct(1L));
  }
}
