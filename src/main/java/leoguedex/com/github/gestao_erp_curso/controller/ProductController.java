package leoguedex.com.github.gestao_erp_curso.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Controlador de Produtos", description = "Controlador para ações relacionadas a Produto")
public class ProductController {

  private final ProductService productService;

  @PostMapping(consumes = "application/json")
  @Operation(summary = "Criação de Produto", description = "Deve criar um produto quando receber todos dados corretamente")
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

  @Hidden
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Exclusão de Produto", description = "Deve apagar um produto, usando o Id Recebido")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Requisição com sucesso"),
      @ApiResponse(responseCode = "404", description = "Objeto com id não localizado"),
      @ApiResponse(responseCode = "500", description = "Erro do servidor")
  })
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(Long.valueOf(id));
    return ResponseEntity.noContent().build();
  }

}
