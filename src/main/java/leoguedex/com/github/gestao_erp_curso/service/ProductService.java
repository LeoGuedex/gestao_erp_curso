package leoguedex.com.github.gestao_erp_curso.service;

import jakarta.validation.Valid;
import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.Product;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ProductRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ProductUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ProductResponseDTO;
import leoguedex.com.github.gestao_erp_curso.exceptions.ProductNotFoundException;
import leoguedex.com.github.gestao_erp_curso.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponseDTO createProduct(@Valid ProductRequestDTO productDTO) {
    Product savedProduct = productRepository.save(
        Product.builder().price(productDTO.price()).name(productDTO.name()).productType(productDTO.productType())
            .build());

    return new ProductResponseDTO(savedProduct.getId(), savedProduct.getPrice(), savedProduct.getName(),
        savedProduct.getProductType());
  }

  public ProductResponseDTO findById(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Produto com id " + id + " não encontrado"));

    return new ProductResponseDTO(product.getId(), product.getPrice(), product.getName(), product.getProductType());
  }

  public List<ProductResponseDTO> findAll() {
    return productRepository.findAll().stream()
        .map(p -> new ProductResponseDTO(p.getId(), p.getPrice(), p.getName(), p.getProductType())).toList();
  }

  public ProductResponseDTO updateProduct(Long id, @Valid ProductUpdateRequestDTO productDTO) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Produto não cadastro com id: " + id));

    product.setName(productDTO.name());
    product.setPrice(productDTO.price());
    Product updatedProduct = productRepository.save(product);

    return new ProductResponseDTO(updatedProduct.getId(), updatedProduct.getPrice(), updatedProduct.getName(),
        updatedProduct.getProductType());
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(findById(id).id());
  }
}
