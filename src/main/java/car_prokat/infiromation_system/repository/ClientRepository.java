package car_prokat.infiromation_system.repository;

import car_prokat.infiromation_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}