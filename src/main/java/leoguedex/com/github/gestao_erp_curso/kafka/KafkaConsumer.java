package leoguedex.com.github.gestao_erp_curso.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  @KafkaListener(topics = "produto-topic", groupId = "demo-group")
  public void listen(String message) {
    System.out.println("Bateu no Listener, do Consumer, a mensagem: " + message);
  }
}