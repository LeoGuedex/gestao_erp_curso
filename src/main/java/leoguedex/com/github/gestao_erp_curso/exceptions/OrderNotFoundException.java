package leoguedex.com.github.gestao_erp_curso.exceptions;

public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(String message) {
    super(message);
  }
}
