package leoguedex.com.github.gestao_erp_curso.repository;

import leoguedex.com.github.gestao_erp_curso.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
