package leoguedex.com.github.gestao_erp_curso.repository;

import leoguedex.com.github.gestao_erp_curso.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
