package leoguedex.com.github.gestao_erp_curso.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ClientResponseDTO;
import leoguedex.com.github.gestao_erp_curso.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

  protected final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<Void> createClient(@RequestBody @Valid ClientRequestDTO clientDTO) {
    log.info("[Client Controller] Inicio da criação do Cliente: {}", clientDTO.getName());
    ClientResponseDTO savedClient = clientService.createClient(clientDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedClient.id()).toUri();

    log.info("[Client Controller] Cliente criado com sucesso, com o id: {}", savedClient.id());
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(produces = "application/json", path = "/{id}")
  public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Integer id) {
    log.info("[Client Controller] Processo de busca de cliente com o id: {}", id);
    ClientResponseDTO client = clientService.findById(Long.valueOf(id));

    if (client == null) {
      log.error("[Client Controller] Cliente não cadastrado com o id: {}", id);
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(client);
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
    List<ClientResponseDTO> clients = clientService.findAll();

    return ResponseEntity.ok(clients);
  }

  @PutMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
  public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Integer id,
      @Valid @RequestBody ClientUpdateRequestDTO clientDTO) {
    ClientResponseDTO result = clientService.updateClient(Long.valueOf(id), clientDTO);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
    clientService.deleteClient(Long.valueOf(id));

    return ResponseEntity.noContent().build();
  }

}
