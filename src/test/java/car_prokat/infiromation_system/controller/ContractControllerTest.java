package car_prokat.infiromation_system.controller;

import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Car;
import car_prokat.infiromation_system.model.Client;
import car_prokat.infiromation_system.model.Contract;
import car_prokat.infiromation_system.repository.CarRepository;
import car_prokat.infiromation_system.repository.ClientRepository;
import car_prokat.infiromation_system.repository.ContractRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestContext.class})
@SpringBootTest
class ContractControllerTest {

    @InjectMocks
    ContractController contractController;

    @Mock
    ContractRepository contractRepository;

    @Mock
    CarRepository carRepository;

    @Mock
    ClientRepository clientRepository;

    @Test
    void getReport() {

        System.out.println("Тест getReport...");

        Contract contract1 = new Contract(1L, null, null);
        Contract contract2 = new Contract(2L, null, null);

        when(contractRepository.findAll()).thenReturn(Arrays.asList(contract1, contract2));

        List<Contract> orders = contractController.getReport();

        Assert.assertEquals(2, orders.size());
        Assert.assertEquals(contract1.getNumber(), orders.get(0).getNumber());
        Assert.assertEquals(contract2.getNumber(), orders.get(1).getNumber());

    }

    @Test
    void getContractById() throws ResourceNotFoundException {

        System.out.println("Тест getContractById...");

        Contract contract = new Contract(1L, null, null);

        when(contractRepository.findById(1L)).thenReturn(java.util.Optional.of(contract));

        ResponseEntity<Contract> responseEntity = contractController.getContractById(1L);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(contract.getNumber(), responseEntity.getBody().getNumber());

    }

    @Test
    void createContract() throws ResourceNotFoundException {

        System.out.println("Тест createContract...");

        Contract contract = new Contract(1L, null, null);

        Car car = new Car("12345678910", "1234", "model1", null, null, null, 500., null, null);
        Client client = new Client("12345678910", "Eva", "Ave", null, null);

        car.setId(1L);
        client.setId(1L);

        contract.setCar(car);
        contract.setClient(client);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        ResponseEntity<Contract> responseEntity = contractController.createContract(contract);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void updateContract() throws ResourceNotFoundException {

        System.out.println("Тест updateContract...");

        Contract contractOld = new Contract(1L, null, null);
        Contract contractNew = new Contract(2L, null, null);

        Car car = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);
        Client client = new Client("12345678910", "Вася", "Никон", null, null);

        car.setId(1L);
        client.setId(1L);

        contractOld.setCar(car);
        contractOld.setClient(client);
        contractNew.setCar(car);
        contractNew.setClient(client);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        when(contractRepository.findById(1L)).thenReturn(java.util.Optional.of(contractOld));

        ResponseEntity<Contract> responseEntity = contractController.updateContract(1L, contractNew);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void deleteContract() throws ResourceNotFoundException {

        System.out.println("Тест deleteContract...");

        Date date = Date.valueOf("1950-03-31");
        Contract contract = new Contract(1L, null, null);

        when(contractRepository.findById(1L)).thenReturn(java.util.Optional.of(contract));

        Map<String, Boolean> response = contractController.deleteContract(1L);

        Assert.assertEquals(Boolean.TRUE, response.get("deleted"));

    }
}