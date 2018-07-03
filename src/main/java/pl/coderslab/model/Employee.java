package pl.coderslab.model;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private String adress;
    private String phone;
    private String notes;
    private double wage;


    public Employee(){

    }
    
    public Employee(int id, String name, String surname, String adress, String phone, String notes, double wage) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.phone = phone;
        this.notes = notes;
        this.wage = wage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}