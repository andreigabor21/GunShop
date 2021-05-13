package ro.ubb.catalog.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.ClientConverter;
import ro.ubb.catalog.web.dto.ClientDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientConverter clientConverter;

    private Client client1;
    private Client client2;
    private ClientDto clientDto1;
    private ClientDto clientDto2;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();
        initData();
    }

    @Test
    public void getClients() throws Exception {
        List<Client> clients = Arrays.asList(client1, client2);
        Set<ClientDto> clientDtos =
                new HashSet<>(Arrays.asList(clientDto1, clientDto2));
        when(clientService.getAllClients()).thenReturn(clients);
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("c1")))
                .andExpect(jsonPath("$[1].name", is("c2")));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(clientService, times(1)).getAllClients();
        verify(clientConverter, times(1)).convertModelsToDtos(clients);
        verifyNoMoreInteractions(clientService, clientConverter);
    }

    @Test
    public void createClient() throws Exception {
        Client client = Client.builder()
                            .name("newClient")
                            .build();
        client.setId(3L);
        ClientDto clientDto = createClientDto(client);
        when(clientConverter.convertDtoToModel(clientDto)).thenReturn(client);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/clients", clientDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(clientDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateClient() throws Exception {
        when(clientService.updateClient(client1))
                .thenReturn(client1);
        when(clientConverter.convertDtoToModel(clientDto1)).thenReturn(client1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/clients/{id}", client1.getId(), clientDto1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(clientDto1)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/clients/{id}", client1.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(clientService, times(1)).deleteClient(client1.getId());
    }

    private void initData() {
        client1 = Client.builder()
                .name("c1")
                .build();
        client1.setId(1L);
        client2 = Client.builder()
                .name("c2")
                .build();
        client2.setId(2L);

        clientDto1 = createClientDto(client1);
        clientDto2 = createClientDto(client2);

    }

    private ClientDto createClientDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .name(client.getName())
                .build();
        clientDto.setId(client.getId());
        return clientDto;
    }

    private String toJsonString(ClientDto clientDto) {
        try {
            return new ObjectMapper().writeValueAsString(clientDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //    private String toJsonString(Map<String, Client
    //
    // Dto> Client
    //
    // DtoMap) {
//        try {
//            return new ObjectMapper().writeValueAsString(Client
//
//         DtoMap);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /*ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/clients/{id}", client1.getId(), clientDto1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(clientDto1)))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("c1")));*/

}
