package leoguedex.com.github.gestao_erp_curso.eventSpring.listeners;

import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoPedidoCriado;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SalvarNoBancoDeDados {

  @EventListener
  public void handler(EventoPedidoCriado evento) {
    //repository
    //valida objeto
    //salva no banco de dados
    System.out.println("[SalvarNoBancoDeDados] Pedido com id: " + evento.pedidoId + " salvo no banco");
  }
}
