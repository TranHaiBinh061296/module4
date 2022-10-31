package cg.wbd.grandemonstration.model.dto;

import java.util.Date;

public class PersonCreateDTO {

    private String fullName;

    private int yob;

    private String vehicleNumber;

    private int departureDay;

    private int departureMonth;

    private int departureYear;

    public PersonCreateDTO() {
    }

    public PersonCreateDTO(String fullName, int yob, String vehicleNumber, int departureDay, int departureMonth, int departureYear) {
        this.fullName = fullName;
        this.yob = yob;
        this.vehicleNumber = vehicleNumber;
        this.departureDay = departureDay;
        this.departureMonth = departureMonth;
        this.departureYear = departureYear;
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

    public int getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(int departureDay) {
        this.departureDay = departureDay;
    }

    public int getDepartureMonth() {
        return departureMonth;
    }

    public void setDepartureMonth(int departureMonth) {
        this.departureMonth = departureMonth;
    }

    public int getDepartureYear() {
        return departureYear;
    }

    public void setDepartureYear(int departureYear) {
        this.departureYear = departureYear;
    }
}
