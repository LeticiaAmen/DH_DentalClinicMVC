package com.dh.DentalClinicMVC.model;

public class Dentist {
    private Integer id;
    private Integer registration;
    private String name;
    private String lastName;

    public Dentist() {
    }

    public Dentist(Integer id, Integer registration, String name, String lastName) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.lastName = lastName;
    }

    public Dentist(Integer registration, String name, String lastName) {
        this.registration = registration;
        this.name = name;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
