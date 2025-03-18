package leoguedex.com.github.gestao_erp_curso.service;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import leoguedex.com.github.gestao_erp_curso.domain.Client;
import leoguedex.com.github.gestao_erp_curso.domain.ItemOrder;
import leoguedex.com.github.gestao_erp_curso.domain.Orders;
import leoguedex.com.github.gestao_erp_curso.domain.Product;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.CreateOrderRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.OrderUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.OrderResponseDTO;
import leoguedex.com.github.gestao_erp_curso.exceptions.ClientNotFoundException;
import leoguedex.com.github.gestao_erp_curso.exceptions.OrderNotFoundException;
import leoguedex.com.github.gestao_erp_curso.repository.ItemOrderRepository;
import leoguedex.com.github.gestao_erp_curso.repository.OrderRepository;
import leoguedex.com.github.gestao_erp_curso.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final ClientService clientService;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final ItemOrderRepository itemOrderRepository;

  public Long createOrder(@Valid CreateOrderRequestDTO dto) {
    Orders order = convertCreateOrderDtoInOrder(dto);
    return orderRepository.save(order).getId();
  }

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

    return createItemOrderByProducts(dto, orderRepository.save(orders));
  }

  private Orders createItemOrderByProducts(CreateOrderRequestDTO dto, Orders savedOrder) {
    dto.products().forEach(p -> {
      Optional<Product> productByName = productRepository.findProductByName(p.getProductName());
      if (productByName.isPresent()) {
        Product product = productByName.get();
        Double itemQuantity = Double.valueOf(p.getQuantity());
        Double totalPriceProduct = p.getQuantity() * product.getPrice();

        ItemOrder savedItem = itemOrderRepository.save(
            new ItemOrder(savedOrder, product, itemQuantity, totalPriceProduct));

        savedOrder.getItens().add(savedItem);
      }
    });

    return orderRepository.save(savedOrder);
  }

  public OrderResponseDTO findById(Long id) {
    Optional<Orders> optionalOrders = orderRepository.findById(id);

    if (optionalOrders.isPresent()) {
      Orders orders = optionalOrders.get();

      return new OrderResponseDTO(orders.getId(), orders.getInstance(), orders.getClient(), orders.getAddress(),
          orders.getPaymentType());
    } else {
      return null;
    }
  }

  public List<OrderResponseDTO> findAll() {
    List<Orders> orderList = orderRepository.findAll();

    return orderList.stream().map(
        order -> new OrderResponseDTO(order.getId(), order.getInstance(), order.getClient(), order.getAddress(),
            order.getPaymentType())).toList();
  }

  public OrderResponseDTO updateOrder(Long id, OrderUpdateRequestDTO orderDTO) {
    Orders orderFounded = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

    Orders ordersUpdated = updateFields(orderFounded, orderDTO);

    Orders savedOrder = orderRepository.save(ordersUpdated);

    return new OrderResponseDTO(savedOrder.getId(), savedOrder.getInstance(), savedOrder.getClient(),
        savedOrder.getAddress(), savedOrder.getPaymentType());
  }

  private Orders updateFields(Orders order, OrderUpdateRequestDTO dto) {
    if (dto.address() != null) {
      order.setAddress(dto.address());
    }

    if (dto.clientId() != null) {
      order.setClient(clientService.getClientById(Long.valueOf(dto.clientId())));
    }

    return order;
  }

  public void deleteOrder(Long id) {
    orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found"));
    orderRepository.deleteById(id);
  }
}
