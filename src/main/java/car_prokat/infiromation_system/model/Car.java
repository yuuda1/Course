package car_prokat.infiromation_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String carVIN;

    @Column(nullable = false)
    @NotNull
    private String carNumber;

    @Column(nullable = false)
    @NotNull
    private String carModel;

    @Column(nullable = false)
    @NotNull
    private String carMake;

    @Column(nullable = false)
    @NotNull
    private String cost;

    @Column(nullable = false)
    @NotNull
    private String type;

    @Column(nullable = false)
    @NotNull
    private double costOnDay;

    @Column(nullable = false)
    @NotNull
    private String carColor;

    @Column(nullable = false)
    @NotNull
    private String carModelYear;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car", orphanRemoval=true)
    private Contract contract;

    @JsonIgnore
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Car() {
    }

    public Car(String carVIN, String carNumber, String carModel, String carMake, String cost, String type, double costOnDay, String carColor, String carModelYear) {
        this.carVIN = carVIN;
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.carMake = carMake;
        this.cost = cost;
        this.type = type;
        this.costOnDay = costOnDay;
        this.carColor = carColor;
        this.carModelYear = carModelYear;
    }


    //    @JsonIgnore
//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCostOnDay() {
        return costOnDay;
    }

    public void setCostOnDay(double costOnDay) {
        this.costOnDay = costOnDay;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(String carModelYear) {
        this.carModelYear = carModelYear;
    }
}
