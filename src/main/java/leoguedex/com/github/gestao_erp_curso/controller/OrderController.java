package leoguedex.com.github.gestao_erp_curso.controller;

import jakarta.validation.Valid;
import java.net.URI;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.CreateOrderRequestDTO;
import leoguedex.com.github.gestao_erp_curso.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequestDTO dto) {
    Long orderId = orderService.createOrder(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderId).toUri();
    return ResponseEntity.created(uri).build();
  }


}
