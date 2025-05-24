package leoguedex.com.github.gestao_erp_curso.eventSpring.listeners;

import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoNotaFiscalGerada;
import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoPedidoCriado;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GerarNotaFiscal {

  private final ApplicationEventPublisher publisher;

  public GerarNotaFiscal(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @EventListener
  public void handler(EventoPedidoCriado evento) {
    System.out.println("[GerarNotaFiscal] Nota fiscal gerada para o pedido: " + evento.pedidoId);
    publisher.publishEvent(new EventoNotaFiscalGerada(evento.pedidoId));
  }
}
