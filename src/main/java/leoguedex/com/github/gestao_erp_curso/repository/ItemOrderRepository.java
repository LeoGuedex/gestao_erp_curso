package leoguedex.com.github.gestao_erp_curso.repository;

import leoguedex.com.github.gestao_erp_curso.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}
