package car_prokat.infiromation_system.repository;

import car_prokat.infiromation_system.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
