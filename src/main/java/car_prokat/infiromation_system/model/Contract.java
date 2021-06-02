package car_prokat.infiromation_system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Long number;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    @NotNull
    private Date dateStart;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    @NotNull
    private Date dateEnd;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval=true)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = {CascadeType.MERGE,  CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval=true)
    @JoinColumn(name = "car_id")
    private Car car;

    public Contract() {
    }


    public Contract(Long number, Date dateStart, Date dateEnd) {
        this.number = number;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }


    public String getDateStart() {
        return dateStart.toString();
    }

    public Date getDateStartSql() {
        return dateStart;
    }

    public void setDateStart(Date date) {
        this.dateStart = date;
    }

    public String getDateEnd() {
        return dateEnd.toString();
    }

    public Date getDateEndSql() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
