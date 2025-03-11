package leoguedex.com.github.gestao_erp_curso.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.CreateOrderRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.OrderUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.OrderResponseDTO;
import leoguedex.com.github.gestao_erp_curso.service.OrderService;
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
@RequestMapping(value = "/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequestDTO dto) {
    Long orderId = orderService.createOrder(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderId).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(produces = "application/json", path = "/{id}")
  public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Integer id) {
    OrderResponseDTO order = orderService.findById(Long.valueOf(id));

    if (order == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(order);
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
    List<OrderResponseDTO> orders = orderService.findAll();

    return ResponseEntity.ok(orders);
  }

  @PutMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
  public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Integer id,
      @Valid @RequestBody OrderUpdateRequestDTO orderDTO) {
    OrderResponseDTO result = orderService.updateOrder(Long.valueOf(id), orderDTO);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
    orderService.deleteOrder(Long.valueOf(id));

    return ResponseEntity.noContent().build();
  }

}
