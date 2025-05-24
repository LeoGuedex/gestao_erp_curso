package leoguedex.com.github.gestao_erp_curso.eventSpring.listeners;

import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoNotaFiscalGerada;
import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoPedidoCriado;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ArmazenarNotaFiscal {

  @EventListener
  public void handler(EventoNotaFiscalGerada evento) {
    System.out.println("[ArmazenarNotaFiscal] Pedido com id: " + evento.pedidoId + ", nota fiscal armazenada");
  }
}
