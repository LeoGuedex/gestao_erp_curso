package leoguedex.com.github.gestao_erp_curso.factory;

public class EmailNotification implements Notification {

  @Override
  public void send(String message) {
    System.out.println("Enviando Email " + message);
  }

  public void dizOi() {

  }
}
