package leoguedex.com.github.gestao_erp_curso.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String topic, String message) {
    kafkaTemplate.send(topic, message);
  }
}