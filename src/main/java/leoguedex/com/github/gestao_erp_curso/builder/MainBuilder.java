package leoguedex.com.github.gestao_erp_curso.builder;

public class MainBuilder {
  public static void main(String[] args) {
    CarroV2 carro2 = CarroV2.builder()
        .motor("2.0")
        .marca("Gol")
        .portas(4)
        .valor(200)
        .ano(1900)
        .build();

    System.out.println("OI");
  }
}
