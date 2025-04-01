package leoguedex.com.github.gestao_erp_curso.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import leoguedex.com.github.gestao_erp_curso.domain.dto.request.ClientRequestDTO;
import leoguedex.com.github.gestao_erp_curso.domain.dto.response.ClientResponseDTO;
import leoguedex.com.github.gestao_erp_curso.domain.enums.Gender;
import leoguedex.com.github.gestao_erp_curso.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(ClientController.class)
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ClientService clientService;

  @Autowired
  private ObjectMapper objectMapper;

  private ClientResponseDTO clientResponseDTO;
  private ClientRequestDTO clientRequestDTO;

  @BeforeEach
  void setUp() {
    clientResponseDTO = new ClientResponseDTO(1L, 0.0, Collections.emptySet(), "Leonardo", "17633564476", 123L, 123L,
        "email@gmail.com", Gender.MALE, 28, null);
    clientRequestDTO = new ClientRequestDTO(Collections.emptySet(), "Leonardo", "17633564776", 123L, 123L,
        "email@gmail.com", "123456", Gender.MALE, 28);
  }

  @Test
  void deveriaCriarUmClienteComSucesso() throws Exception {
    when(clientService.createClient(any(ClientRequestDTO.class))).thenReturn(clientResponseDTO);

    mockMvc.perform(
            post("/clients").contentType("application/json").content(objectMapper.writeValueAsString(clientRequestDTO)))
        .andExpect(status().isCreated());
  }

  @Test
  void deveriaEstourarExceptionQuandoCriarClienteFalho() throws Exception {
    when(clientService.createClient(any(ClientRequestDTO.class))).thenThrow(
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar cliente"));

    mockMvc.perform(
            post("/clients")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientRequestDTO)))
        .andExpect(status().isBadRequest());
  }

}

