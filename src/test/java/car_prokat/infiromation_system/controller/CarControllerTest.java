package car_prokat.infiromation_system.controller;

import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Car;
import car_prokat.infiromation_system.repository.CarRepository;
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

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestContext.class})
@SpringBootTest
class CarControllerTest {

    @InjectMocks
    CarController carController;

    @Mock
    CarRepository carRepository;

    @Test
    void getAllCar() {

        System.out.println("Тест getAllCar...");
        Car car1 = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);
        Car car2 = new Car("426265623", "ав23цу", "Конь", null, null, null, 500., null, null);
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<Car> cars = carController.getAllCar();

        Assert.assertEquals(2, cars.size());
        Assert.assertEquals(car1.getCarVIN(), cars.get(0).getCarVIN());
        Assert.assertEquals(car2.getCarVIN(), cars.get(1).getCarVIN());

    }

    @Test
    void getCarStatic() {
        System.out.println("Тест getCarStatic...");

        Car car1 = new Car("623464255", "а34кцк", "Порш", null, null, "Бизнес", 500., null, null);
        Car car2 = new Car("426265623", "ав23цу", "Конь", null, null, "Премиум", 500., null, null);
        Car car3 = new Car("154325123", "аj83цу", "Пес", null, null, "Премиум", 500., null, null);

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2, car3));

        Map<String, Integer> response = carController.getCarStatic();

        Assert.assertEquals(new Integer(1), response.get("Бизнес"));
        Assert.assertEquals(new Integer(2), response.get("Премиум"));

    }

    @Test
    void getCarById() throws ResourceNotFoundException {


        System.out.println("Тест getCarById...");
        Car car1 = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);

        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car1));

        ResponseEntity<Car> responseEntity = carController.getCarById(1L);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(car1.getCarVIN(), responseEntity.getBody().getCarVIN());

    }

    @Test
    void createCar() {

        System.out.println("Тест createCar...");

        Car car = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);

        ResponseEntity<Car> responseEntity = carController.createCar(car);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void updateCar() throws ResourceNotFoundException {

        System.out.println("Тест updateCar...");

        Car carOld = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);
        Car carNew = new Car("426265623", "ав23цу", "Конь", null, null, null, 500., null, null);

        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(carOld));

        ResponseEntity<Car> responseEntity = carController.updateCar(1L, carNew);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void deleteCar() throws ResourceNotFoundException {

        System.out.println("Тест deleteCar...");

        Car car = new Car("623464255", "а34кцк", "Порш", null, null, null, 500., null, null);

        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        Map<String, Boolean> response = carController.deleteCar(1L);

        Assert.assertEquals(Boolean.TRUE, response.get("deleted"));

    }
}