package cg.wbd.grandemonstration.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private int yob;

    private String vehicleNumber;

    private Date departureDay;

    public Person() {
    }

    public Person(Long id, String fullName, int yob, String vehicleNumber, Date departureDay) {
        this.id = id;
        this.fullName = fullName;
        this.yob = yob;
        this.vehicleNumber = vehicleNumber;
        this.departureDay = departureDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Date getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(Date departureDay) {
        this.departureDay = departureDay;
    }
}
