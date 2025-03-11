package leoguedex.com.github.gestao_erp_curso.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ProductRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ProductUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ProductResponseDTO;
import leoguedex.com.github.gestao_erp_curso.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping(consumes = "application/json")
  public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequestDTO productDTO) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(productService.createProduct(productDTO).id()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @GetMapping(produces = "application/json", path = "/{id}")
  public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Integer id) {
    return ResponseEntity.ok(productService.findById(Long.valueOf(id)));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @PutMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
  public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id,
      @Valid @RequestBody ProductUpdateRequestDTO productDTO) {
    return ResponseEntity.ok(productService.updateProduct(Long.valueOf(id), productDTO));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(Long.valueOf(id));
    return ResponseEntity.noContent().build();
  }

}
