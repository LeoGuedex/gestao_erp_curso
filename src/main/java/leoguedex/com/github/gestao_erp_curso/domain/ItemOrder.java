package leoguedex.com.github.gestao_erp_curso.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrder {

  @JsonIgnore
  @EmbeddedId
  private ItemOrderPK id = new ItemOrderPK();

  private Double quantity;
  private Double price;

  public ItemOrder(Orders orders, Product product, Double quantity, Double price) {
    this.id.setOrders(orders);
    this.id.setProduct(product);
    this.quantity = quantity;
    this.price = price;
  }

  public Double getTotalPrice() {
    return price * quantity;
  }

  public Orders getOrder() {
    return this.id.getOrders();
  }

  public void setOrder(Orders orders) {
    id.setOrders(orders);
  }

  public Product getProduct() {
    return this.id.getProduct();
  }

  public void setProduct(Product product) {
    id.setProduct(product);
  }
}
