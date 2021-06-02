package car_prokat.infiromation_system.controller;

import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Car;
import car_prokat.infiromation_system.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/carprokat/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    private CarRepository carRepository;


    //get car
    @GetMapping("cars")
    public List<Car> getAllCar() {
        return this.carRepository.findAll();
    }

    //get car
    @GetMapping("cars/static")
    public Map<String, Integer> getCarStatic() {
        Map<String, Integer> response = new HashMap<>();;

        for (Car c : this.carRepository.findAll()) {
            if (response.containsKey(c.getType())){
                response.put(c.getType(), response.get(c.getType())+1);
            } else
                response.put(c.getType(),1);
        }
        return response;
    }


    // get car by id
    @GetMapping("cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id:: " + carId));
        return ResponseEntity.ok().body(car);
    }

    //save car
    @PostMapping("cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }

    // update car
    @PutMapping("cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId,
                                         @Validated @RequestBody Car carDetail)
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

        return ResponseEntity.ok(this.carRepository.save(car));
    }

    // delete car
    @DeleteMapping("cars/{id}")
    public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id:: " + carId));
        this.carRepository.delete(car);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
