package leoguedex.com.github.gestao_erp_curso.builder;

import lombok.Setter;

@Setter
public class Carro {

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

  public Carro(CarroBuilder builder){
    this.motor = builder.motor;
    this.portas = builder.portas;
    this.marca = builder.marca;
    this.temArCondicionado = builder.temArCondicionado;
    this.ano = builder.ano;
  }

  public static class CarroBuilder {

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

    public CarroBuilder motor(String motor) {
      this.motor = motor;
      return this;
    }

    public CarroBuilder portas(Integer portas) {
      this.portas = portas;
      return this;
    }

    public CarroBuilder marca(String marca) {
      this.marca = marca;
      return this;
    }

    public CarroBuilder temArCondicionado(boolean temArCondicionado) {
      this.temArCondicionado = temArCondicionado;
      return this;
    }

    public CarroBuilder ano(Integer ano) {
      this.ano = ano;
      return this;
    }

    public CarroBuilder transmissao(String transmissao) {
      this.transmissao = transmissao;
      return this;
    }

    public CarroBuilder valor(Integer valor) {
      this.valor = valor;
      return this;
    }

    public CarroBuilder modelo(String modelo) {
      this.modelo = modelo;
      return this;
    }

    public CarroBuilder cor(String cor) {
      this.cor = cor;
      return this;
    }

    public CarroBuilder peso(Double peso) {
      this.peso = peso;
      return this;
    }

    public Carro construir(){
      return new Carro(this);
    }
  }
}
