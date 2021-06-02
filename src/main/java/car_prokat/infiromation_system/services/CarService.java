package car_prokat.infiromation_system.services;

import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Car;
import car_prokat.infiromation_system.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {

    @Autowired
    private static CarRepository carRepository;

    public static List<Car> getAllCar() {

        return carRepository.findAll();
    }

    public static ResponseEntity<Car> getCarById(Long carId)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id:: " + carId));
        return ResponseEntity.ok().body(car);
    }

    public static ResponseEntity<Car> createCar(Car car) {
        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }

    public static ResponseEntity<Car> updateCar(Long carId, Car carDetail)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id:: " + carId));

        car.setCarNumber(carDetail.getCarNumber());
        car.setType(carDetail.getType());
        car.setCarColor(carDetail.getCarColor());
        car.setCarModel(carDetail.getCarModel());
        car.setCarModelYear(carDetail.getCarModelYear());
        car.setCarVIN(carDetail.getCarVIN());
        car.setCost(carDetail.getCost());
        car.setCostOnDay(carDetail.getCostOnDay());
        car.setCarMake(carDetail.getCarMake());

        return ResponseEntity.ok(CarService.carRepository.save(car));
    }

    public static Map<String, Boolean> deleteCar(Long carId)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id:: " + carId));
        CarService.carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
