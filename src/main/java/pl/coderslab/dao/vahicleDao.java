package pl.coderslab.dao;

import java.time.LocalDate;

public class vahicleDao {

    private int id =0;
    private String model;
    private String brand;
    private int produced;
    private String registration;
    private LocalDate nextInspection;
    private int customerId;

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

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



    public vahicleDao(String model, String brand, int produced, String registration, LocalDate nextInspection, int customerId) {
        this.model = model;
        this.brand = brand;
        this.produced = produced;
        this.registration = registration;
        this.nextInspection = nextInspection;
        this.customerId = customerId;
    }
}
