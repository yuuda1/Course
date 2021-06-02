package car_prokat.infiromation_system.controller;

import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Client;
import car_prokat.infiromation_system.repository.ClientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestContext.class})
@SpringBootTest
class ClientControllerTest {

    @InjectMocks
    ClientController clientController;

    @Mock
    ClientRepository clientRepository;

    @Test
    void getAllClient() {
        System.out.println("Тест getAllClient...");

        Client client1 = new Client("12345678910", "Вася", "Никон", null, null);
        Client client2 = new Client("12345678911", "Артем", "Продакшн", null, null);

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> clients = clientController.getAllClient();

        Assert.assertEquals(2, clients.size());
        Assert.assertEquals(client1.getFirstName(), clients.get(0).getFirstName());
        Assert.assertEquals(client2.getFirstName(), clients.get(1).getFirstName());

    }

    @Test
    void getClientById() throws ResourceNotFoundException {

        System.out.println("Тест getClientById...");
        Client client1 = new Client("12345678910", "Вася", "Никон", null, null);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client1));

        ResponseEntity<Client> responseEntity = clientController.getClientById(1L);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(client1.getFirstName(), responseEntity.getBody().getFirstName());

    }

    @Test
    void createClient() {

        System.out.println("Тест createClient...");

        Client client = new Client("12345678910", "Вася", "Никон", null, null);

        ResponseEntity<Client> responseEntity = clientController.createClient(client);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void updateClient() throws ResourceNotFoundException {

        System.out.println("Тест updateClient...");

        Client clientOld = new Client("12345678910", "Вася", "Никон", null, null);
        Client clientNew = new Client("12345678911", "Артем", "Продакшн", null, null);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(clientOld));

        ResponseEntity<Client> responseEntity = clientController.updateClient(1L, clientNew);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void deleteClient() throws ResourceNotFoundException {

        System.out.println("Тест deleteClient...");

        Client client = new Client("12345678910", "Вася", "Никон", null, null);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));

        Map<String, Boolean> response = clientController.deleteClient(1L);

        Assert.assertEquals(Boolean.TRUE, response.get("deleted"));

    }
}