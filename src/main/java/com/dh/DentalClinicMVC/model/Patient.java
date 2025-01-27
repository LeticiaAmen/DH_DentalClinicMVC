package com.dh.DentalClinicMVC.model;

import java.time.LocalDate;

public class Patient {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private Integer cardIdentity;
    private LocalDate admissionOfDate;
    private Address address;

    public Patient() {
    }

    public Patient(Integer id, String name, String lastName, String email, Integer cardIdentity, LocalDate admissionOfDate, Address address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cardIdentity = cardIdentity;
        this.admissionOfDate = admissionOfDate;
        this.address = address;
    }

    public Patient(String name, String lastName, String email, Integer cardIdentity, LocalDate admissionOfDate, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cardIdentity = cardIdentity;
        this.admissionOfDate = admissionOfDate;
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCardIdentity(Integer cardIdentity) {
        this.cardIdentity = cardIdentity;
    }

    public void setAdmissionOfDate(LocalDate admissionOfDate) {
        this.admissionOfDate = admissionOfDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getCardIdentity() {
        return cardIdentity;
    }

    public LocalDate getAdmissionOfDate() {
        return admissionOfDate;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
