package com.dh.DentalClinicMVC.model;

public class Address {
    private Integer id;
    private String street;
    private Integer number;
    private String location;
    private String province;

    public Address(Integer id, String street, Integer number, String location, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }

    public Address(String street, Integer number, String location, String province) {
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getProvince() {
        return province;
    }
}
