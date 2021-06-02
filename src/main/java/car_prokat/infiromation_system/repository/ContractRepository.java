package car_prokat.infiromation_system.repository;

import car_prokat.infiromation_system.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
//    @Query("SELECT id, date_end, date_start, (date_end- date_start) \"counOfDay\", \"number\", car_id, client_id\n" +
//            "\tFROM public.contracts;")
//    List<?> getReport();
//
//    @Query("SELECT (date_end- date_start)" +
//            "\tFROM public.contracts;")
//    int getDay();
}
