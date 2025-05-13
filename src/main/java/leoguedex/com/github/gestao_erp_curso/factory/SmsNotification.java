package leoguedex.com.github.gestao_erp_curso.factory;

public class SmsNotification implements Notification {

  @Override
  public void send(String message) {
    System.out.println("Enviando SMS " + message);
  }
}
