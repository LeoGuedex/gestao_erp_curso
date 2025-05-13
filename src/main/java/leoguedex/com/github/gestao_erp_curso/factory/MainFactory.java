package leoguedex.com.github.gestao_erp_curso.factory;

public class MainFactory {

  public static void main(String[] args) {
    Notification n1 = NotificationFactory.createNotification("email");
    n1.send("Email de Bem vindo");

    Notification n2 = NotificationFactory.createNotification("sms");
    n2.send("Sms de Rec de Senha");
  }
}
