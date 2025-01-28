package leoguedex.com.github.gestao_erp_curso.service;

import leoguedex.com.github.gestao_erp_curso.domain.Client;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ClientResponseDTO;
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
    clientToSave.setAge(clientDTO.getAge());

    Client savedClient = clientRepository.save(clientToSave);
    ClientResponseDTO result = new ClientResponseDTO(savedClient.getId(), savedClient.getBalance(),
        savedClient.getPhoneNumbers(), savedClient.getOrders(), savedClient.getName(), savedClient.getCpf(),
        savedClient.getLongitude(), savedClient.getLatitude(), savedClient.getEmail(), savedClient.getGender(),
        savedClient.getAge());

    return result;
  }

}
