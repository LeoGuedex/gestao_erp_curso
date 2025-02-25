package leoguedex.com.github.gestao_erp_curso.service;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import leoguedex.com.github.gestao_erp_curso.domain.Client;
import leoguedex.com.github.gestao_erp_curso.domain.Orders;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.CreateOrderRequestDTO;
import leoguedex.com.github.gestao_erp_curso.exceptions.ClientNotFoundException;
import leoguedex.com.github.gestao_erp_curso.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public Long createOrder(@Valid CreateOrderRequestDTO dto) {
    Orders order = convertCreateOrderDtoInOrder(dto);
    return orderRepository.save(order).getId();
  }

  private final ClientService clientService;

  public Orders convertCreateOrderDtoInOrder(@Valid CreateOrderRequestDTO dto) {
    Orders orders = new Orders();
    orders.setInstance(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    orders.setAddress(dto.address());
    orders.setPaymentType(dto.paymentType());

    Optional<Client> optClient = clientService.returnClientById(dto.client());
    if (optClient.isPresent()) {
      orders.setClient(optClient.get());
    } else {
      throw new ClientNotFoundException("Cliente nao localizado, com o id: " + dto.client());
    }

    return orders;
  }
}
