package leoguedex.com.github.gestao_erp_curso.builder;

import lombok.Builder;

@Builder
public class CarroV2 {

  private String motor;
  private Integer portas;
  private String marca;
  private boolean temArCondicionado;
  private Integer ano;
  private String transmissao;
  private Integer valor;
  private String modelo;
  private String cor;
  private Double peso;
}
