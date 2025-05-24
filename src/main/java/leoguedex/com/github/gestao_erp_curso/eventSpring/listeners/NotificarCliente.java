package leoguedex.com.github.gestao_erp_curso.eventSpring.listeners;

import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoPedidoCriado;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificarCliente {

  @EventListener
  public void handler(EventoPedidoCriado evento) {
    System.out.println("[NotificarCliente] Pedido criado com o id: " + evento.pedidoId);
  }
}
