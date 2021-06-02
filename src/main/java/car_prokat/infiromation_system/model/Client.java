package car_prokat.infiromation_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String phoneNumber;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    @NotNull
    private String ssn;

    @Column(nullable = false)
    @NotNull
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval=true)
    private Contract contracts;

    @JsonIgnore
    public Contract getContracts() {
        return contracts;
    }

    public void setContracts(Contract contracts) {
        this.contracts = contracts;
    }

    public Client() {
    }

    public Client(String phoneNumber, String firstName, String lastName, String ssn, String email) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
    }

    //    @JsonIgnore
//    public Contract getContracts() {
//        return contracts;
//    }
//
//    public void setContracts(Contract contracts) {
//        this.contracts = contracts;
//    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
