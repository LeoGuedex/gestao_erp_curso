package leoguedex.com.github.gestao_erp_curso.service;

import java.util.List;
import java.util.Optional;
import leoguedex.com.github.gestao_erp_curso.domain.Client;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientUpdateRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ClientResponseDTO;
import leoguedex.com.github.gestao_erp_curso.exceptions.ClientNotFoundException;
import leoguedex.com.github.gestao_erp_curso.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public ClientResponseDTO createClient(ClientRequestDTO clientDTO) {
    Client clientToSave = new Client();
    clientToSave.setPhoneNumbers(clientDTO.getPhoneNumbers());
    clientToSave.setName(clientDTO.getName());
    clientToSave.setCpf(clientDTO.getCpf());
    clientToSave.setLatitude(clientDTO.getLatitude());
    clientToSave.setLongitude(clientDTO.getLongitude());
    clientToSave.setEmail(clientDTO.getEmail());
    clientToSave.setPassword(clientDTO.getPassword());
    clientToSave.setGender(clientDTO.getGender());
    clientToSave.setBalance(0.0);
    clientToSave.setAge(clientDTO.getAge());

    Client savedClient = clientRepository.save(clientToSave);
    ClientResponseDTO result = new ClientResponseDTO(savedClient.getId(), savedClient.getBalance(),
        savedClient.getPhoneNumbers(), savedClient.getOrders(), savedClient.getName(), savedClient.getCpf(),
        savedClient.getLongitude(), savedClient.getLatitude(), savedClient.getEmail(), savedClient.getGender(),
        savedClient.getAge());

    return result;
  }

  public ClientResponseDTO findById(Long id) {
    Optional<Client> optionalClient = clientRepository.findById(id);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.get();

      ClientResponseDTO result = new ClientResponseDTO(client.getId(), client.getBalance(), client.getPhoneNumbers(),
          client.getOrders(), client.getName(), client.getCpf(), client.getLongitude(), client.getLatitude(),
          client.getEmail(), client.getGender(), client.getAge());

      return result;
    } else {
      return null;
    }
  }

  public List<ClientResponseDTO> findAll() {
    List<Client> clientList = clientRepository.findAll();

    List<ClientResponseDTO> dtoList = clientList.stream().map(client -> {
      return new ClientResponseDTO(client.getId(), client.getBalance(), client.getPhoneNumbers(), client.getOrders(),
          client.getName(), client.getCpf(), client.getLongitude(), client.getLatitude(), client.getEmail(),
          client.getGender(), client.getAge());
    }).toList();

    return dtoList;
  }

  public ClientResponseDTO updateClient(Long id, ClientUpdateRequestDTO clientDTO) {
    Client clientFounded = clientRepository.findById(id)
        .orElseThrow(() -> new ClientNotFoundException("Client Not Found"));

    Client clientUpdated = updateFields(clientFounded, clientDTO);

    Client savedClient = clientRepository.save(clientUpdated);

    return new ClientResponseDTO(savedClient.getId(), savedClient.getBalance(), savedClient.getPhoneNumbers(),
        savedClient.getOrders(), savedClient.getName(), savedClient.getCpf(), savedClient.getLongitude(),
        savedClient.getLatitude(), savedClient.getEmail(), savedClient.getGender(), savedClient.getAge());
  }

  private Client updateFields(Client client, ClientUpdateRequestDTO clientDTO) {
    if (clientDTO.balance() != null) {
      client.setBalance(clientDTO.balance());
    }

    if (clientDTO.phoneNumbers() != null) {
      client.setPhoneNumbers(clientDTO.phoneNumbers());
    }

    if (clientDTO.name() != null) {
      client.setName(clientDTO.name());
    }

    if (clientDTO.latitude() != null) {
      client.setLatitude(clientDTO.latitude());
    }

    if (clientDTO.longitude() != null) {
      client.setLongitude(clientDTO.longitude());
    }

    if (clientDTO.gender() != null) {
      client.setGender(clientDTO.gender());
    }

    client.setEmail(clientDTO.email());
    client.setPassword(clientDTO.password());

    return client;
  }

  public void deleteClient(Long id) {
    clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client Not Found"));
    clientRepository.deleteById(id);
  }

}
