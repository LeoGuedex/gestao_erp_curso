package leoguedex.com.github.gestao_erp_curso.repository;

import java.util.Optional;
import leoguedex.com.github.gestao_erp_curso.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//  @Query(value = "SELECT * FROM Product WHERE name = :name", nativeQuery = true)
//  Product findProductByRelatedName(@Param("name") String name);

  Optional<Product> findProductByName(String name);
}
