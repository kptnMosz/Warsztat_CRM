package pl.coderslab.model;

import pl.coderslab.DateUtil;
import pl.coderslab.dao.CustomerDao;
import java.sql.Date;
import java.time.LocalDate;

public class Vehicle {

    //    ---------========zmienne===========-----------
    private int id = 0;
    private String model;
    private String brand;
    private int produced;
    private String registration;
    private LocalDate nextInspection;
    private int customerId;


    //    ------=====zmienne pomocnicze======------
    private Customer customer;

    //        ---------========gettery i settery===========-----------


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDate getNextInspection() {
        return nextInspection;
    }

    public Date getNextInspectionInSqlFormat() {

        if (nextInspection == null) {
            return null;
        }
        return Date.valueOf(nextInspection);
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }

    /**
     * seter do ustawiania  daty ze stringa
     * format daty musi byc taki:
     * rrrr-mm-dd lub rrrr/mm/dd
     *
     * @return true jesli format tekstu byl prawidlowy, false jesli nie
     * jesli format jest nieprawidlowy, data nie zostanie zmieniona
     */
    public boolean setNextInspectionFromString(String nextInspectionString) {
        LocalDate bufor = DateUtil.setDateFormString(nextInspectionString);
        boolean sukces = bufor != null;
        if (sukces) {
            this.nextInspection = bufor;
            return true;
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customer = CustomerDao.loadById(customerId);
        this.customerId = customerId;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //            ---------========konstruktory===========-----------
    public Vehicle(String model, String brand, int produced, String registration, LocalDate nextInspection, int customerId) {
        this.model = model;
        this.brand = brand;
        this.produced = produced;
        this.registration = registration;
        this.nextInspection = nextInspection;
        this.customerId = customerId;
    }

    public Vehicle(String model, String brand, int produced, String registration, String nextInspection, int customerId) {
        this.model = model;
        this.brand = brand;
        this.produced = produced;
        this.registration = registration;
        setNextInspectionFromString(nextInspection);
        setCustomerId(customerId);
    }

    public Vehicle(int id, String model, String brand, int produced, String registration, LocalDate nextInspection, int customerId) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.produced = produced;
        this.registration = registration;
        this.nextInspection = nextInspection;
        setCustomerId(customerId);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", produced=" + produced +
                ", registration='" + registration + '\'' +
                ", nextInspection=" + nextInspection +
                ", customerId=" + customerId +
                '}';
    }

}
