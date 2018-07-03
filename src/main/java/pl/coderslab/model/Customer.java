package pl.coderslab.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Customer {
    private int id = 0;
    private String name;
    private String surname;
    private String birthdayDate;

    public Customer(){

    }


    public Customer(int id, String name, String surname, String birthdayDate) {
        this.id = 0;
        setName(name);
        setSurname(surname);
        setBirthdayDate(birthdayDate);
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

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate (String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdayDate=" + birthdayDate +
                '}';
    }


}
