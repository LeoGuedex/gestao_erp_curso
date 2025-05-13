package leoguedex.com.github.gestao_erp_curso.factory;

public class NotificationFactory {

  public static Notification createNotification(String type) {
    if (type.equalsIgnoreCase("Email")){
      return new EmailNotification();
    } else if (type.equalsIgnoreCase("sms")){
      return new SmsNotification();
    } else {
      throw new IllegalArgumentException("Tipo Invalido");
    }
  }
}
