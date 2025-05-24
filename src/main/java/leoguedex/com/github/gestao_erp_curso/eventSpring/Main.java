package leoguedex.com.github.gestao_erp_curso.eventSpring;

import leoguedex.com.github.gestao_erp_curso.eventSpring.eventos.EventoPedidoCriado;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private final ApplicationEventPublisher publisher;

  public Main(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }


  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    publisher.publishEvent(new EventoPedidoCriado(1234584L));
  }
}
