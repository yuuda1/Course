package car_prokat.infiromation_system.controller;


import car_prokat.infiromation_system.exception.ResourceNotFoundException;
import car_prokat.infiromation_system.model.Car;
import car_prokat.infiromation_system.model.Client;
import car_prokat.infiromation_system.model.Contract;
import car_prokat.infiromation_system.repository.CarRepository;
import car_prokat.infiromation_system.repository.ClientRepository;
import car_prokat.infiromation_system.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/carprokat/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractController {



    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;


    //get contract
    @GetMapping("contracts")
    public List<Contract> getReport() {
        return this.contractRepository.findAll();
    }


    // get contract by id
    @GetMapping("/contracts/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable(value = "id") Long contractId)
            throws ResourceNotFoundException {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found for this id:: " + contractId));
        return ResponseEntity.ok().body(contract);
    }

    //save contract
    @PostMapping("contracts")
    public ResponseEntity<Contract> createContract(@Valid @RequestBody Contract contract) throws ResourceNotFoundException {

        Car car = carRepository.findById(contract.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id:: " + contract.getCar().getId()));

        Client client = clientRepository.findById(contract.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id:: " + contract.getClient().getId()));

        contract.setClient(client);
        contract.setCar(car);

        return new ResponseEntity<>(contractRepository.save(contract), HttpStatus.OK);
    }

    // update contract
    @PutMapping("contracts/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable(value = "id") Long contractId,
                                             @Validated @RequestBody Contract contractDetails)
            throws ResourceNotFoundException {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found for this id:: " + contractId));

        Car car = carRepository.findById(contractDetails.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id:: " + contractDetails.getCar().getId()));

        Client client = clientRepository.findById(contractDetails.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id:: " + contractDetails.getClient().getId()));


        contract.setNumber(contractDetails.getNumber());
        contract.setDateStart(contractDetails.getDateStartSql());
        contract.setDateEnd(contractDetails.getDateEndSql());

        contract.setClient(client);
        contract.setCar(car);

        return ResponseEntity.ok(this.contractRepository.save(contract));
    }

    // delete contract
    @DeleteMapping("contracts/{id}")
    public Map<String, Boolean> deleteContract(@PathVariable(value = "id") Long contractId)
            throws ResourceNotFoundException {
//        Contract contract = contractRepository.findById(contractId)
//                .orElseThrow(() -> new ResourceNotFoundException("Contract not found for this id:: " + contractId));
        this.contractRepository.deleteById(contractId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
