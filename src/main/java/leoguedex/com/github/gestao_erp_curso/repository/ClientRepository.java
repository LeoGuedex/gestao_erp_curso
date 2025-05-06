package leoguedex.com.github.gestao_erp_curso.repository;

import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  Client findByEmail(String email);
}
