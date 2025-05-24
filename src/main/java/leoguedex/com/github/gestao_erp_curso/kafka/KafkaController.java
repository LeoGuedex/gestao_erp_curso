package leoguedex.com.github.gestao_erp_curso.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

  @Autowired
  private KafkaProducer producer;

  @PostMapping
  public void sendMessage(@RequestParam String msg) {
    producer.send("produto-topic", msg);
  }
}